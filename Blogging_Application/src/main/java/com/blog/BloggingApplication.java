package com.blog;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BloggingApplication implements CommandLineRunner {

	private @Autowired PasswordEncoder passwordEncoder;

	private static final Logger LOGGER = LogManager.getLogger(BloggingApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BloggingApplication.class, args);
		LOGGER.info("Spring Boot Application Started...!!!");
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(passwordEncoder.encode("Rajeev@123"));
	}

}
