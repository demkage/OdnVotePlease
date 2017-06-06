package com.rusifer.odnvtepls.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abosii on 6/6/2017.
 */
@Configuration
public class RESTConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        return restTemplate;
    }
}
