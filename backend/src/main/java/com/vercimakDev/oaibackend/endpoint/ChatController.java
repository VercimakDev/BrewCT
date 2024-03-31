package com.vercimakDev.oaibackend.endpoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vercimakDev.oaibackend.endpoint.dto.ChatRequest;
import com.vercimakDev.oaibackend.endpoint.dto.ChatResponse;
import com.vercimakDev.oaibackend.endpoint.dto.CustomFeedbackXml;
import com.vercimakDev.oaibackend.entity.Feedback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;


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

    @Autowired
    private ObjectMapper objectMapper;
    @GetMapping("/chat")
    public ChatResponse chat(@RequestParam String prompt) {
        log.info("Starting new chat with prompt: {}", prompt);
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            // Now specifying maxTokens when creating ChatRequest
            ChatRequest chatRequest = new ChatRequest(model, prompt);

            String body = objectMapper.writeValueAsString(chatRequest);
            log.info("Request body: {}", body);
            HttpEntity<String> request = new HttpEntity<>(body, headers);
            log.info("Created a new request: {}", request);

            // Use exchange method to get access to response headers
            ResponseEntity<ChatResponse> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, request, ChatResponse.class);

            if (responseEntity.getBody() == null || responseEntity.getBody().getChoices() == null || responseEntity.getBody().getChoices().isEmpty()) {
                log.error("Error: No Response");
                return null;
            }
            // Log the response
            log.info("The OpenAI API returned the following: {}", responseEntity.getBody().toString());
            return responseEntity.getBody();
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return null;
        }
    }

    @PostMapping(value = "/feedback", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public String createFeedbackXml(@RequestBody Feedback feedback) {
        try {
            log.info("This is the prompt: {}", feedback.getPrompt());
            log.info("This is the feedback: {}", feedback.toString());
            CustomFeedbackXml customFeedbackXml = new CustomFeedbackXml();
            // Populate the customFeedbackXml object from the feedback object
            customFeedbackXml.setTimestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            customFeedbackXml.setPrompt(feedback.getPrompt());
            customFeedbackXml.setResponseText(feedback.getResponseText());
            customFeedbackXml.setFeedbackRating(feedback.getRating());
            customFeedbackXml.setFeedbackText(feedback.getFeedback());

            // Create an XmlMapper
            XmlMapper xmlMapper = new XmlMapper();
            // Serialize the CustomFeedbackXml object to XML
            String xml = xmlMapper.writeValueAsString(customFeedbackXml);

            Files.createDirectories(Path.of("output"));
            String fileName = "feedback_" + System.currentTimeMillis() + ".xml";
            Path outputPath = Path.of("output", fileName);
            Files.write(outputPath, xml.getBytes(), StandardOpenOption.CREATE);
            return "XML file saved to: " + outputPath.toString();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Error converting to XML";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error saving XML file";
        }
    }

    private String addMetadataToXml(String xml) {
        // Create an XML string with metadata
        return "<metadata>" +
                "<timestamp>" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "</timestamp>" +
                "</metadata>\n" + xml;
    }
}
