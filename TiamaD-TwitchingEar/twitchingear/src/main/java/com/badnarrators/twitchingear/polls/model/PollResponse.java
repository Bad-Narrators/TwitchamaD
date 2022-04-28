package com.badnarrators.twitchingear.polls.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.badnarrators.twitchingear.polls.entity.Poll;
import com.badnarrators.twitchingear.polls.entity.PollVote;

public class PollResponse {

    private String id;
    private String question;
    private Map<Integer, PollVote> answers;
    private Date creationDate;
    private Date deletionDate;

    public PollResponse() {
        this.id = "0";
        this.question = "Request error, value not found";
        this.answers = new HashMap<>();
        this.creationDate = new Date();
        this.deletionDate = new Date();
    }
    public PollResponse(String error) {
        this.id = "0";
        this.question = error;
        this.answers = new HashMap<>();
        this.creationDate = new Date();
        this.deletionDate = new Date();
    }

    public PollResponse(Poll p){
        this.id = p.getId().toString();
        this.question = p.getQuestion();
        this.answers = p.getAnswers();
        this.creationDate = p.getCreationDate();
        this.deletionDate = p.getDeletionDate();
    }

    public PollResponse(String id, String question, Map<Integer, PollVote> answers) {
        this.id = id;
        this.question = question;
        this.answers = answers;
        this.creationDate = Calendar.getInstance().getTime();
        this.deletionDate = new Date();
    }

    public PollResponse(String id, String question, Map<Integer, PollVote> answers, Date creationDate) {
        this.id = id;
        this.question = question;
        this.answers = answers;
        this.creationDate = creationDate;
        this.deletionDate = new Date();
    }
    public PollResponse(String id, String question, Map<Integer, PollVote> answers, Date creationDate, Date deletionDate) {
        this.id = id;
        this.question = question;
        this.answers = answers;
        this.creationDate = creationDate;
        this.deletionDate = deletionDate;
    }

    public String getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public Map<Integer, PollVote> getAnswers() {
        return answers;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getDeletionDate(){
        return deletionDate;
    }

}
