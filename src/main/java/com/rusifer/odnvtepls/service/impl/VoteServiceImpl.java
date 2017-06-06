package com.rusifer.odnvtepls.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rusifer.odnvtepls.domain.Poll;
import com.rusifer.odnvtepls.domain.Response;
import com.rusifer.odnvtepls.domain.User;
import com.rusifer.odnvtepls.service.VoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by abosii on 6/6/2017.
 */
@Service
public class VoteServiceImpl implements VoteService {

    private static final Logger logger = LoggerFactory.getLogger(VoteServiceImpl.class);

    RestTemplate restTemplate;
    private String urlRegister;
    private String urlPoll;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    public void setUrlRegister(@Value("${com.rusifer.odnvtepls.registerUrl}") String urlRegister) {
        this.urlRegister = urlRegister;
    }

    @Autowired
    public void setUrlPoll(@Value("${com.rusifer.odnvtepls.pollUrl}") String urlPoll) {
        this.urlPoll = urlPoll;
    }

    @Override
    public Response registerUser(User user) {
        logger.info("Try register : " + user);
        Optional<Response> response = null;

        try {

            response =  response.ofNullable(restTemplate.postForObject(urlRegister, user, Response.class));

        } catch (HttpClientErrorException e) {
            logger.error(e.getMessage());
            response = Optional.ofNullable(readBodyOnError(e));
        }


        return response.orElseGet(() -> new Response());
    }

    @Override
    public Response vote(Poll poll) {
        logger.info("Try vote : " + poll);
        Optional<Response> response = Optional.empty();
        try {
            response = Optional.ofNullable(restTemplate.postForObject(urlPoll, poll, Response.class));
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            logger.error(e.getMessage());
            response = Optional.ofNullable(readBodyOnError(e));
        }

        return response.orElseGet(() -> new Response());
    }

    private Response readBodyOnError(HttpStatusCodeException exception) {
        Response response = null;
        String responseString = exception.getResponseBodyAsString();

        ObjectMapper mapper = new ObjectMapper();

        try {
            response = mapper.readValue(responseString,
                    Response.class);
        } catch (IOException e) {
            logger.info("Error: " + e.getMessage());
        }

        logger.info("Response: " + response);

        return response;
    }
}
