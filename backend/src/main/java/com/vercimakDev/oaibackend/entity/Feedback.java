package com.vercimakDev.oaibackend.entity;


public class Feedback {
    private Prompt prompt;
    private String responseText;
    private int rating;
    private String feedback;

    public Feedback(Prompt prompt, String responseText, int rating, String feedback) {
        this.prompt = prompt;
        this.responseText = responseText;
        this.rating = rating;
        this.feedback = feedback;
    }

    public Prompt getPrompt() {
        return prompt;
    }

    public void setPrompt(Prompt prompt) {
        this.prompt = prompt;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "{" +
                "\"prompt\":\"" + prompt + "\"" +
                ", \"responseText\":\"" + responseText + "\"" +
                ", \"rating\":\"" + rating + "\"" +
                ", \"feedback\":\"" + feedback + "\"" +
                '}';
    }
}
