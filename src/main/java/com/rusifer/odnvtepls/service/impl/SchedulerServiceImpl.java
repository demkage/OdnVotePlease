package com.rusifer.odnvtepls.service.impl;

import com.rusifer.odnvtepls.domain.Poll;
import com.rusifer.odnvtepls.domain.Response;
import com.rusifer.odnvtepls.domain.Statistic;
import com.rusifer.odnvtepls.domain.User;
import com.rusifer.odnvtepls.service.SchedulerService;
import com.rusifer.odnvtepls.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by abosii on 6/6/2017.
 */
@Service
public class SchedulerServiceImpl implements SchedulerService {
    private VoteService voteService;
    private Lock continuityLock = new ReentrantLock();
    private ThreadPoolTaskScheduler threadPool;

    private long awaitTime;
    private boolean stop;

    private TaskForce lastTaskForce;

    @Autowired
    public void setVoteService(VoteService voteService) {
        this.voteService = voteService;
    }

    @Autowired
    public void setAwaitTime(@Value("${com.rusifer.odnvtepls.awaitTime}") long awaitTime) {
        this.awaitTime = awaitTime;
    }

    @Autowired
    public void setTaskScheduler(ThreadPoolTaskScheduler threadPool) {
        this.threadPool = threadPool;
    }

    @Override
    public Response test(User user, Poll poll) {
        stop = false;


        Response registerResponse = voteService.registerUser(user);

        lastTaskForce = new TaskForce(user, poll);
        threadPool.submit(lastTaskForce);

        return registerResponse;
    }

    @Override
    public void stop() {
        continuityLock.lock();
        stop = true;
        continuityLock.unlock();
    }

    @Override
    public Statistic getHistory() {
        return lastTaskForce.getStatistic();
    }

    private class TaskForce implements Runnable {
        private User user;
        private Poll poll;
        private Statistic statistic;

        public TaskForce(User user, Poll poll) {
            this.user = user;
            this.poll = poll;
            statistic = new Statistic();
            statistic.setHistory(new ArrayList<>());
        }


        public Statistic getStatistic() {
            return statistic;
        }

        public void run() {
            while (continuityLock.tryLock() && stop == false) {
                Response response = voteService.vote(poll);
                statistic.count();

                if (response.getStatus() != null && response.getStatus().equals("ok"))
                    statistic.success();
                else
                    statistic.error();

                statistic.getHistory().add(response);

                continuityLock.unlock();
                try {
                    Thread.sleep(awaitTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
