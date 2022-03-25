package com.badnarrators.twitchingear.model;

public class PollResponse {
    
    private final String id;
    private final String content;

    public PollResponse(String id, String content) {
        this.id = id;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
