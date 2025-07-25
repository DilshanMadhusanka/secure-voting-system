package com.example.demo.dto;

public class VoteRequest {
    private String candidate;
    private String token;

    public VoteRequest() {
    }

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
