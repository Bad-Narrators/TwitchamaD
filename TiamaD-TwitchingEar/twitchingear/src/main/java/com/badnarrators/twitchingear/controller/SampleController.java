package com.badnarrators.twitchingear.controller;

import java.util.concurrent.atomic.AtomicLong;

import com.badnarrators.twitchingear.model.SampleResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
    
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/test")
    public SampleResponse test(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new SampleResponse(counter.incrementAndGet(), String.format(template, name));
    }
}
