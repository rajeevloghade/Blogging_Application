package com.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dao.IUserDao;
import com.blog.entities.User;
import com.blog.exception.ResourceNotFoundException;
import com.blog.service.IUserService;
import com.blog.wrapper.UserWrapper;

@Service
public class UserServiceImpl implements IUserService {

	private @Autowired IUserDao userDao;
	private @Autowired ModelMapper modelMapper;

	@Override
	public UserWrapper createUser(UserWrapper userWrapper) {
		return entityToWrapper(userDao.save(wrapperToEntity(userWrapper)));
	}

	@Override
	public UserWrapper updateUser(UserWrapper userWrapper, Integer userId) {
		User userById = userDao.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		userById.setName(userWrapper.getName());
		userById.setEmail(userWrapper.getEmail());
		userById.setAbout(userWrapper.getAbout());
		userById.setPassword(userWrapper.getPassword());
		return entityToWrapper(userDao.save(userById));
	}

	@Override
	public UserWrapper getUserById(Integer userId) {
		return entityToWrapper(
				userDao.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId)));
	}

	@Override
	public List<UserWrapper> getAllUser() {
		return userDao.findAll().stream().map(user -> entityToWrapper(user)).collect(Collectors.toList());
	}

	@Override
	public void deleteUser(Integer userId) {
		userDao.delete(
				userDao.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId)));
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
