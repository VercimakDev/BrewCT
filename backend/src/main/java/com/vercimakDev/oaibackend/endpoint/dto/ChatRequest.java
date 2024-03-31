package com.vercimakDev.oaibackend.endpoint.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vercimakDev.oaibackend.entity.Message;
import org.springframework.http.HttpEntity;

import java.util.ArrayList;
import java.util.List;

public class ChatRequest {

    private String model;
    private List<Message> messages;
    private int n;
    private double temperature;

    @JsonProperty("max_tokens")
    private int maxTokens;

    public ChatRequest(String model, String prompt) {
        this.model = model;
        this.n = 1;
        this.temperature = 0.6;
        this.messages = new ArrayList<>();
        this.messages.add(new Message("user", prompt));
        this.maxTokens = 600;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getMaxTokens() {
        return maxTokens;
    }

    public void setMaxTokens(int maxTokens) {
        this.maxTokens = maxTokens;
    }

    // convert the object to a json HttpEntity<String>
    public HttpEntity<String> toHttpEntity() {
        return new HttpEntity<>(this.toString());
    }
    @Override
    public String toString() {
        return "{" +
                "\"model\":\"" + model + '\"' +
                ", \"messages\":" + messages +
                ", \"n\":" + n + // Removed the quotes as n is an integer
                ", \"temperature\":" + temperature + // Removed the quotes as temperature is a double
                ", \"max_tokens\":" + maxTokens + // Add the max_tokens parameter
                '}';
    }
}
