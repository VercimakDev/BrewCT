package com.vercimakDev.oaibackend.endpoint.dto;
import com.vercimakDev.oaibackend.entity.Message;

import java.util.List;

public class ChatResponse {

    private List<Choice> choices;

    public ChatResponse() {
        // Default constructor
    }

    public ChatResponse(List<Choice> choices) {
        this.choices = choices;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public static class Choice {

        private int index;
        private Message message;

        public Choice() {
            // Default constructor
        }

        public Choice(int index, Message message) {
            this.index = index;
            this.message = message;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public Message getMessage() {
            return message;
        }

        public void setMessage(Message message) {
            this.message = message;
        }
    }
}
