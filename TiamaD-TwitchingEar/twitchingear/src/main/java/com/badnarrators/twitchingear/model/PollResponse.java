package com.badnarrators.twitchingear.model;

import java.util.ArrayList;
import java.util.List;

public class PollResponse {

    private final String id;
    private final String question;
    private final List<String> answers;
    private final List<Integer> votes;

    public PollResponse() {
        this.id = "0";
        this.question = "Missing ID in request";
        this.answers = new ArrayList<>();
        this.votes = new ArrayList<>();
    }

    public PollResponse(String id, String question, List<String> answers, List<Integer> votes) {
        this.id = id;
        this.question = question;
        this.answers = answers;
        this.votes = votes;
    }

    public String getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public List<Integer> getVotes() {
        return votes;
    }

}
