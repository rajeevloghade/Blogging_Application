package com.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.service.IUserService;
import com.blog.utils.IConstants;
import com.blog.utils.Response;
import com.blog.wrapper.UserWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@Api(tags = "User", description = "Provides User APIs")
@RestController
@RequestMapping("api/user")
public class UserRestController {

	private static final Logger LOGGER = LogManager.getLogger(UserRestController.class);
	private static final Logger EMAIL = LogManager.getLogger("EMAIL");

	private @Autowired IUserService userService;

	@ApiOperation(value = "This API is used to create user.", tags = "User", authorizations = {
			@Authorization(value = "JWT") }, response = Response.class)
	@PostMapping("createUser")
	public ResponseEntity<UserWrapper> createUser(@Valid @RequestBody UserWrapper userWrapper) {
		LOGGER.info("Inside createUser in UserRestController method started with userWrapper: {}", userWrapper);
		return new ResponseEntity<>(userService.createUser(userWrapper), HttpStatus.CREATED);
	}

	@ApiOperation(value = "This API is used to update user.", tags = "User", authorizations = {
			@Authorization(value = "JWT") }, response = Response.class)
	@PutMapping("updateUser/{userId}")
	public ResponseEntity<UserWrapper> updateUser(@Valid @RequestBody UserWrapper userWrapper,
			@PathVariable("userId") Integer userId) {
		LOGGER.info("Inside updateUser in UserRestController method started with userWrapper: {},userId: {}",
				userWrapper, userId);
		return ResponseEntity.ok(userService.updateUser(userWrapper, userId));
	}

	@ApiOperation(value = "This API is used to delete user.", tags = "User", authorizations = {
			@Authorization(value = "JWT") }, response = Response.class)
//	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("deleteUser/{userId}")
	public ResponseEntity<Response> deleteUser(@PathVariable("userId") Integer userId) {
		LOGGER.info("Inside deleteUser in UserRestController method started with userId: {}", userId);
		userService.deleteUser(userId);
		return new ResponseEntity<Response>(new Response("true", "User deleted successfully", null), HttpStatus.OK);
	}

	@ApiOperation(value = "This API is used to get all users.", tags = "User", authorizations = {
			@Authorization(value = "JWT") }, response = Response.class)
	@GetMapping("getAllUsers")
	public ResponseEntity<List<UserWrapper>> getAllUsers(
			@RequestParam(name = "pageNumber", required = false, defaultValue = IConstants.PAGE_NUMBER) Integer pageNumber,
			@RequestParam(name = "pageSize", required = false, defaultValue = IConstants.PAGE_SIZE) Integer pageSize,
			@RequestParam(name = "sortBy", required = false, defaultValue = "userId") String sortBy) {
		LOGGER.info(
				"Inside getAllUsers in UserRestController method started with pageNumber: {},pageSize: {},sortBy: {}",
				pageNumber, pageSize, sortBy);
		return ResponseEntity.ok(userService.getAllUser(pageNumber, pageSize, sortBy));
	}

	@ApiOperation(value = "This API is used to get user by userId", tags = "User", authorizations = {
			@Authorization(value = "JWT") }, response = Response.class)
	@GetMapping("getUserById/{userId}")
	public ResponseEntity<UserWrapper> getUserById(@PathVariable("userId") Integer userId) {
		LOGGER.info("Inside getUserById in UserRestController method started with userId: {}", userId);
		return ResponseEntity.ok(userService.getUserById(userId));
	}

	@ApiOperation(value = "This API is used to search user", tags = "User", authorizations = {
			@Authorization(value = "JWT") }, response = Response.class)
	@GetMapping("searchUser/{userName}")
	public ResponseEntity<List<UserWrapper>> searchUser(@PathVariable("userName") String userName) {
		LOGGER.info("Inside searchUser in UserRestController method started with userName: {}", userName);
		return ResponseEntity.ok(userService.searchUser(userName));
	}

}
