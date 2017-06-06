package com.rusifer.odnvtepls.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * Created by abosii on 6/6/2017.
 */
@Configuration
public class SchedulerConfig {

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler =
                new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(10);
        threadPoolTaskScheduler.setThreadNamePrefix("OdnVotePlease");
        return threadPoolTaskScheduler;
    }
}
