package com.vercimakDev.oaibackend.endpoint.dto;

import com.vercimakDev.oaibackend.entity.Message;
import org.springframework.http.HttpEntity;

import java.util.ArrayList;
import java.util.List;

public class ChatRequest {

    private String model;
    private List<Message> messages;
    private int n;
    private double temperature;

    public ChatRequest(String model, String prompt) {
        this.model = model;
        this.n = 1;
        this.messages = new ArrayList<>();
        this.messages.add(new Message("user", prompt));
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

    // convert the object to a json HttpEntity<String>
    public HttpEntity<String> toHttpEntity() {
        return new HttpEntity<>(this.toString());
    }
    @Override
    public String toString() {
        return "{" +
                "\"model\":\"" + model + '\"' +
                ", \"messages\":" + messages +
                ", \"n\":\"" + n +"\""+
                ", \"temperature\":\"" + temperature +"\""+
                '}';
    }
}
