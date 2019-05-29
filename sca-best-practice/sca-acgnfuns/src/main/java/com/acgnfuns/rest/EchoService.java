package com.acgnfuns.rest;

import com.acgnfuns.config.FeignConfiguration;
import com.acgnfuns.rest.impl.EchoServiceFallbackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "user-center", fallback = EchoServiceFallbackImpl.class, configuration = FeignConfiguration.class)
public interface EchoService {
    @RequestMapping(value = "/example/testSentinel", method = RequestMethod.GET)
    String testSentinel();
}
