package com.example.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class NotificationApplication {

	@KafkaListener(topics = "notification-topic")
	public void handleNotificationMessage(String message) {
		// Here you would send the notification to all users.
		System.out.println("Notification to users: " + message);
	}

	public static void main(String[] args) {
		SpringApplication.run(NotificationApplication.class, args);
	}
}
