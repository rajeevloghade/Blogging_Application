package com.blog;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.blog.dao.IUserRoleDao;
import com.blog.entities.UserRole;
import com.blog.utils.IConstants;

@SpringBootApplication
public class BloggingApplication implements CommandLineRunner {

	private static final Logger LOGGER = LogManager.getLogger(BloggingApplication.class);

	private @Autowired IUserRoleDao userRoleDao;

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

		try {
			UserRole admin = new UserRole();
			admin.setUserId(IConstants.ADMIN_USER);
			admin.setName("ADMIN");

			UserRole normal = new UserRole();
			normal.setUserId(IConstants.NORMAL_USER);
			normal.setName("NORMAL");
			userRoleDao.saveAll(Arrays.asList(admin, normal));
		} catch (Exception exception) {
			LOGGER.error("Exception occured while inserting roles : {}", exception);
		}

	}

}
