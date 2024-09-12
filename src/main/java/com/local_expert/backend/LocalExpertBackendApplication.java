package com.local_expert.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LocalExpertBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocalExpertBackendApplication.class, args);
	}

}

//netstat -ano | findstr :8080
//taskkill /PID <PID> /F
