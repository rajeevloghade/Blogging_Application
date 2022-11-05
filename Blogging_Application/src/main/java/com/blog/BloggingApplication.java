package com.blog;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BloggingApplication {

	private static final Logger LOGGER = LogManager.getLogger(BloggingApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BloggingApplication.class, args);
		LOGGER.info("Spring Boot Application Started...!!!");
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
