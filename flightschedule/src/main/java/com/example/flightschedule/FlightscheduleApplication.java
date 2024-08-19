package com.example.flightschedule;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@RequestMapping("/flights")
public class FlightscheduleApplication {

	private final KafkaTemplate<String, String> kafkaTemplate;

	public FlightscheduleApplication(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	@PostMapping
	public void postFlight(@RequestParam String from, @RequestParam String to, @RequestParam String time) {
		String message = String.format("Flight from %s to %s at %s", from, to, time);
		System.out.println("Sending message: " + message);
		kafkaTemplate.send("flight-topic", message);
	}

	public static void main(String[] args) {
		SpringApplication.run(FlightscheduleApplication.class, args);
	}
}
