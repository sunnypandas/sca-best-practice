package com.acgnfuns.service;

import com.acgnfuns.service.AnimationBangumiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AnimationBangumiServiceTests {

    @Autowired
    private AnimationBangumiService animationBangumiService;

    @Test
    public void testTest() {
        animationBangumiService.test();
    }
}
