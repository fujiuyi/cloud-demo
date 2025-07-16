package com.fujiuyi.order.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ProductServiceConfig {

    /**
     * 开启feign日志
     * @return
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * feign默认的重试策略，100ms重试一次，总共重试5次，最大间隔1000ms
     * @return
     */
//    @Bean
//    Retryer feignRetryer() {
//        return new Retryer.Default();
//    }
}
