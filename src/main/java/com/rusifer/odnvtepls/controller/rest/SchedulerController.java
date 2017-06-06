package com.rusifer.odnvtepls.controller.rest;

import com.rusifer.odnvtepls.domain.Poll;
import com.rusifer.odnvtepls.domain.Response;
import com.rusifer.odnvtepls.domain.Statistic;
import com.rusifer.odnvtepls.domain.User;
import com.rusifer.odnvtepls.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by abosii on 6/6/2017.
 */
@RestController
public class SchedulerController {
    private SchedulerService schedulerService;

    @Autowired
    public void setSchedulerService(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    @RequestMapping(value = "/start", method = RequestMethod.POST)
    public Response start(@RequestBody User user) {
        Poll poll = new Poll(user.getEmail(), 1);
        return schedulerService.test(user, poll);
    }

    @RequestMapping(value = "/stop", method = RequestMethod.GET)
    public int stop() {
        schedulerService.stop();
        return 1;
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public Statistic history() {
        return schedulerService.getHistory();
    }
}
