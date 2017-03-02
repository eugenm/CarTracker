package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.example.config.JpaConfiguration;

@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages = { "com.example" })
public class CarsApplication {

	public static final Logger logger = LoggerFactory.getLogger(CarsApplication.class);

	public static void main(String[] args) {
		logger.info("Starting CarTracking Application");
		SpringApplication.run(CarsApplication.class, args);
	}

}
