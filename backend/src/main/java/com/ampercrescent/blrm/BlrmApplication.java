package com.ampercrescent.blrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BlrmApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlrmApplication.class, args);
	}

}
