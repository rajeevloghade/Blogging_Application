package com.blog.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.blog.dao.IUserDao;

@SpringBootTest
class BloggingApplicationTests {

	private @Autowired IUserDao userDao;

	@Test
	void contextLoads() {
	}

	@Test
	public void repositoryTest() {
		System.out.println(userDao.getClass().getName());
		System.out.println(userDao.getClass().getPackageName());
	}

}
