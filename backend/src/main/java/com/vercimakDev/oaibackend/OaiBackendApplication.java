package com.vercimakDev.oaibackend;

import config.OpenAIRestTemplateConfig;
import controller.ChatController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = OpenAIRestTemplateConfig.class)
@ComponentScan(basePackageClasses = ChatController.class)
public class OaiBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(OaiBackendApplication.class, args);
	}

}
