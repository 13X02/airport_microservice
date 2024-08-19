package com.example.airport;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;

@SpringBootApplication
@Service
public class AirportApplication {

	private final KafkaTemplate<String, String> kafkaTemplate;

	public AirportApplication(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	@KafkaListener(topics = "survey-topic")
	public void handleSurveyMessage(String message) {

		String notificationMessage = "Notification: " + message;
		System.out.println("Sending message: " + message);

		kafkaTemplate.send("notification-topic", notificationMessage);
	}

	public static void main(String[] args) {
		SpringApplication.run(AirportApplication.class, args);
	}
}
