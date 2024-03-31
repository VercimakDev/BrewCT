package com.vercimakDev.oaibackend.endpoint.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.vercimakDev.oaibackend.entity.Prompt;

@JacksonXmlRootElement(localName = "feedback")
public class CustomFeedbackXml {
    @JacksonXmlProperty(localName = "timestamp")
    private String timestamp;

    @JacksonXmlProperty(localName = "prompt")
    private Prompt prompt;

    @JacksonXmlProperty(localName = "responseText")
    private String responseText;

    @JacksonXmlProperty(localName = "feedbackRating")
    private int feedbackRating;

    @JacksonXmlProperty(localName = "feedbackText")
    private String feedbackText;

    public CustomFeedbackXml() {
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
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

    public int getFeedbackRating() {
        return feedbackRating;
    }

    public void setFeedbackRating(int feedbackRating) {
        this.feedbackRating = feedbackRating;
    }

    public String getFeedbackText() {
        return feedbackText;
    }

    public void setFeedbackText(String feedbackText) {
        this.feedbackText = feedbackText;
    }
}

