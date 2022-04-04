package com.badnarrators.twitchingear.polls.entity;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Poll {
    private UUID id;
    private String question;
    private Map<Integer, PollVote> answers;
    private List<String> targets;    

    public Poll(String question, Map<Integer, PollVote> answers, List<String> targets) {
        this.id = UUID.randomUUID();
        this.question = question;
        this.answers = answers;
        this.targets = targets;
    }

    public UUID getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public Map<Integer, PollVote> getAnswers() {
        return answers;
    }

    public void vote(int id) {
        this.answers.get(id).vote();
    }

    public List<String> getTargets() {
        return targets;
    }

    public void setTargets(List<String> targets) {
        this.targets = targets;
    }
}
