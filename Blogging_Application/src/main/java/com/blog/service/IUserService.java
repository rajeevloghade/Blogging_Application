package com.blog.service;

import java.util.List;

import com.blog.wrapper.UserWrapper;

public interface IUserService {

	UserWrapper createUser(UserWrapper user);

	UserWrapper updateUser(UserWrapper user, Integer userId);

	UserWrapper getUserById(Integer userId);

	void deleteUser(Integer userId);

	List<UserWrapper> getAllUser(Integer pageNumber, Integer pageSize, String sortBy);
}
