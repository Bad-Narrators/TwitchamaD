package com.badnarrators.twitchingear.controller;

import java.util.ArrayList;
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
    
    private final AtomicLong counter = new AtomicLong();

    @GetMapping(value="/get")
    @ResponseBody
    public PollResponse response(@RequestParam(required = false) String id) {
        if(id == null) {
            return new PollResponse ();
        }

        //Test variables, will be changed later
        String question = "Vecchia Londra o Messicano?";
        ArrayList<String> answers = new ArrayList<String>();
        answers.add("Vecchia Londra");
        answers.add("Messicano");
        ArrayList<Integer> votes = new ArrayList<Integer>();
        votes.add(250);
        votes.add(12);

        return new PollResponse(id, question, answers, votes);
    }
}
