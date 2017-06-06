package com.rusifer.odnvtepls.service;

import com.rusifer.odnvtepls.domain.Poll;
import com.rusifer.odnvtepls.domain.Response;
import com.rusifer.odnvtepls.domain.User;
import org.springframework.stereotype.Service;

/**
 * Created by abosii on 6/6/2017.
 */
public interface VoteService {
    Response registerUser(User user);

    Response vote(Poll poll);
}
