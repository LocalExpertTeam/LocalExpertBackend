package com.example.LocalExpertBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@SpringBootApplication
public class LocalExpertBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocalExpertBackendApplication.class, args);
	}

}

//netstat -ano | findstr :8080
//taskkill /PID <PID> /F
