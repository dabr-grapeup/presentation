package com.grapeup.digitaltwinmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DigitalTwinManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalTwinManagerApplication.class, args);
	}
}
