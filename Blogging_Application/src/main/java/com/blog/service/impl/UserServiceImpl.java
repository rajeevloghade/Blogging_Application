package com.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.controller.CommentRestController;
import com.blog.dao.IUserDao;
import com.blog.dao.IUserRoleDao;
import com.blog.entities.User;
import com.blog.entities.UserRole;
import com.blog.exception.ResourceNotFoundException;
import com.blog.service.IUserService;
import com.blog.utils.IConstants;
import com.blog.wrapper.UserWrapper;

@Service
public class UserServiceImpl implements IUserService {

	private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);
	private static final Logger EMAIL = LogManager.getLogger("EMAIL");

	private @Autowired IUserDao userDao;
	private @Autowired ModelMapper modelMapper;
	private @Autowired PasswordEncoder passwordEncoder;
	private @Autowired IUserRoleDao userRoleDao;

	@Override
	public UserWrapper registerUser(UserWrapper userWrapper) {
		LOGGER.info("Inside registerUser in UserServiceImpl method started with userWrapper: {}", userWrapper);
		User user = modelMapper.map(userWrapper, User.class);
		// password encoded
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		// roles
		UserRole userRole = userRoleDao.findById(IConstants.NORMAL_USER).get();
		user.getRoles().add(userRole);
		User newUser = userDao.save(user);
		return entityToWrapper(newUser);
	}

	@Override
	public UserWrapper createUser(UserWrapper userWrapper) {
		LOGGER.info("Inside createUser in UserServiceImpl method started with userWrapper: {}", userWrapper);
		userWrapper.setPassword(passwordEncoder.encode(userWrapper.getPassword()));
		return entityToWrapper(userDao.save(wrapperToEntity(userWrapper)));
	}

	@Override
	public UserWrapper updateUser(UserWrapper userWrapper, Integer userId) {
		LOGGER.info("Inside updateUser in UserServiceImpl method started with userWrapper: {},userId: {}",
				userWrapper, userId);
		User userById = userDao.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		userById.setName(userWrapper.getName());
		userById.setEmail(userWrapper.getEmail());
		userById.setAbout(userWrapper.getAbout());
		userById.setPassword(passwordEncoder.encode(userWrapper.getPassword()));
		return entityToWrapper(userDao.save(userById));
	}

	@Override
	public UserWrapper getUserById(Integer userId) {
		LOGGER.info("Inside getUserById in UserServiceImpl method started with userId: {}", userId);
		return entityToWrapper(
				userDao.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId)));
	}

	@Override
	public List<UserWrapper> getAllUser(Integer pageNumber, Integer pageSize, String sortBy) {
		LOGGER.info(
				"Inside getAllUsers in UserServiceImpl method started with pageNumber: {},pageSize: {},sortBy: {}",
				pageNumber, pageSize, sortBy);
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
		Page<User> pagePosts = userDao.findAll(pageable);
		List<User> allUsers = pagePosts.getContent();
		return allUsers.stream().map(user -> entityToWrapper(user)).collect(Collectors.toList());
	}

	@Override
	public void deleteUser(Integer userId) {
		LOGGER.info("Inside deleteUser in UserServiceImpl method started with userId: {}", userId);
		userDao.delete(
				userDao.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId)));
	}

	@Override
	public List<UserWrapper> searchUser(String userName) {
		LOGGER.info("Inside searchUser in UserServiceImpl method started with userName: {}", userName);
		return userDao.findByNameContaining(userName).stream().map(category -> entityToWrapper(category))
				.collect(Collectors.toList());
	}

	public User wrapperToEntity(UserWrapper userWrapper) {
		return modelMapper.map(userWrapper, User.class);
//		user.setUserId(userWrapper.getUserId());
//		user.setName(userWrapper.getName());
//		user.setEmail(userWrapper.getEmail());
//		user.setPassword(userWrapper.getPassword());
//		user.setAbout(userWrapper.getAbout());
//		return user;
	}

	public UserWrapper entityToWrapper(User user) {
		return modelMapper.map(user, UserWrapper.class);
//		userWrapper.setUserId(user.getUserId());
//		userWrapper.setName(user.getName());
//		userWrapper.setEmail(user.getEmail());
//		userWrapper.setPassword(user.getPassword());
//		userWrapper.setAbout(user.getAbout());
//		return userWrapper;
	}

}
