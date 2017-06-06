package com.rusifer.odnvtepls.service;

import com.rusifer.odnvtepls.domain.Poll;
import com.rusifer.odnvtepls.domain.Response;
import com.rusifer.odnvtepls.domain.Statistic;
import com.rusifer.odnvtepls.domain.User;

import java.util.List;

/**
 * Created by abosii on 6/6/2017.
 */
public interface SchedulerService {
    Response test(User user, Poll poll);

    void stop();

    Statistic getHistory();
}
