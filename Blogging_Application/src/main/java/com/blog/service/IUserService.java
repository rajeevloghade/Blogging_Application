package com.blog.service;

import java.util.List;

import com.blog.wrapper.UserWrapper;

public interface IUserService {

	UserWrapper createUser(UserWrapper user);

	UserWrapper updateUser(UserWrapper user, Integer userId);

	UserWrapper getUserById(Integer userId);

	List<UserWrapper> getAllUser();

	void deleteUser(Integer userId);
}
