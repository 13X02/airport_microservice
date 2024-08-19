package com.example.atc;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;

@SpringBootApplication
@Service
public class AtcApplication {

	private final KafkaTemplate<String, String> kafkaTemplate;

	public AtcApplication(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	@KafkaListener(topics = "flight-topic")
	public void handleFlightMessage(String message) {
		Random rand = new Random();

		String surveyMessage = message +" "+ rand.nextInt(10)+ " - Runway assigned";
		System.out.println("Sending message: " + surveyMessage);
		kafkaTemplate.send("survey-topic", surveyMessage);
	}

	public static void main(String[] args) {
		SpringApplication.run(AtcApplication.class, args);
	}
}
