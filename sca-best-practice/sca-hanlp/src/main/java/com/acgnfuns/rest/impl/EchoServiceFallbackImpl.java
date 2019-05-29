package com.acgnfuns.rest.impl;

import com.acgnfuns.rest.EchoService;

public class EchoServiceFallbackImpl implements EchoService {

    @Override
    public String testSentinel() {
        return "echo fallback";
    }
}
