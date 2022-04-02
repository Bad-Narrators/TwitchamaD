package com.badnarrators.twitchingear.model;

import java.util.ArrayList;

import org.apache.tomcat.jni.Poll;

public class PollResponse {
    
    private final String id;
    private final String question;
    private final ArrayList<String> answers;
    private final ArrayList<Integer> votes;

    public PollResponse() {
        this.id = "0";
        this.question = "Missing ID in request";
        this.answers = new ArrayList<>();
        this.votes = new ArrayList<>();
    }

    public PollResponse(String id, String question, ArrayList<String> answers, ArrayList<Integer> votes) {
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

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public ArrayList<Integer> getVotes() {
        return votes;
    }

}
