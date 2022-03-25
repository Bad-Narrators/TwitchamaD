package com.badnarrators.twitchingear.controller;

import java.util.concurrent.atomic.AtomicLong;

import com.badnarrators.twitchingear.model.PollResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/poll")
public class PollController {
    
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping(value="/get")
    @ResponseBody
    public PollResponse response(@RequestParam(required = false) String id) {
        if(id == null) {
            return new PollResponse ("0", "This value was null");
        }
        return new PollResponse(id, String.format(template, id));
    }
}
