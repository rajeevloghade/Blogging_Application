package com.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.blog.dao.IUserDao;
import com.blog.entities.User;
import com.blog.service.IUserService;
import com.blog.wrapper.UserWrapper;

public class UserServiceImpl implements IUserService {

	private @Autowired IUserDao userDao;

	@Override
	public UserWrapper createUser(UserWrapper user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserWrapper updateUser(UserWrapper user, Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserWrapper getUserById(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserWrapper> getAllUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub

	}

	public User wrapperToEntity(UserWrapper userWrapper) {
		User user = new User();
		user.setUserId(userWrapper.getUserId());
		user.setName(userWrapper.getName());
		user.setEmail(userWrapper.getEmail());
		user.setPassword(userWrapper.getPassword());
		user.setAbout(userWrapper.getAbout());
		return user;
	}

	public UserWrapper entityToWrapper(User user) {
		UserWrapper userWrapper = new UserWrapper();
		userWrapper.setUserId(user.getUserId());
		userWrapper.setName(user.getName());
		userWrapper.setEmail(user.getEmail());
		userWrapper.setPassword(user.getPassword());
		userWrapper.setAbout(user.getAbout());
		return userWrapper;
	}
}
