package com.vercimakDev.oaibackend;

import com.vercimakDev.oaibackend.config.OpenAIRestTemplateConfig;
import com.vercimakDev.oaibackend.endpoint.ChatController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class OaiBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(OaiBackendApplication.class, args);
	}

}
