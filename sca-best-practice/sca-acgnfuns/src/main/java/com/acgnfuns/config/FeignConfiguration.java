package com.acgnfuns.config;

import com.acgnfuns.rest.impl.EchoServiceFallbackImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {
    @Bean
    public EchoServiceFallbackImpl echoServiceFallbackImpl() {
        return new EchoServiceFallbackImpl();
    }
}
