package com.blog.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blog.dao.IUserDao;
import com.blog.entities.User;
import com.blog.exception.ResourceNotFoundException;

@Service
public class CustomeUserDetailService implements UserDetailsService {

	private static final Logger LOGGER = LogManager.getLogger(CustomeUserDetailService.class);
	private static final Logger EMAIL = LogManager.getLogger("EMAIL");

	private @Autowired IUserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LOGGER.info("Inside loadUserByUsername in CustomeUserDetailService method started with username: {}", username);
		// loading user from DB by username
		User userByEmail = userDao.findByEmail(username)
				.orElseThrow(() -> new ResourceNotFoundException("User", "email : " + username, 0));
		return userByEmail;
	}

}
