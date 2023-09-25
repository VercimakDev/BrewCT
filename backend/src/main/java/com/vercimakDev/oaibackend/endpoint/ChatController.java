package com.vercimakDev.oaibackend.endpoint;

import com.vercimakDev.oaibackend.endpoint.dto.ChatRequest;
import com.vercimakDev.oaibackend.endpoint.dto.ChatResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping(value = "/api/v1")
@Slf4j
public class ChatController {

    @Qualifier("openaiRestTemplate")
    @Autowired
    private RestTemplate restTemplate;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiUrl;

    @GetMapping("/chat")
    public ChatResponse chat(@RequestParam String prompt) {
        log.info("Starting new chat with prompt: {}", prompt);
        // create a request
        ChatRequest request = new ChatRequest(model, prompt);
        log.info("Created a new request: {}", request);
        // call the API
        ChatResponse response = restTemplate.postForObject(apiUrl, request, ChatResponse.class);

        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            log.error("Error: No Response");
            return null;
        }

        // return the first response
        log.info("The OpenAI API returned the following: {}", response.toString());
        return response;
    }
}
