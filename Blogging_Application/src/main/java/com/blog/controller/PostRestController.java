package com.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.service.IFileService;
import com.blog.service.IPostService;
import com.blog.utils.FileResponse;
import com.blog.utils.IConstants;
import com.blog.utils.PostResponse;
import com.blog.utils.Response;
import com.blog.wrapper.PostWrapper;
import com.google.gson.Gson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@Api(tags = "Post", description = "Provides Post APIs")
@RestController
@RequestMapping("api/post")
public class PostRestController {

	private static final Logger LOGGER = LogManager.getLogger(PostRestController.class);
	private static final Logger EMAIL = LogManager.getLogger("EMAIL");

	private @Autowired IPostService postService;
	private @Autowired IFileService fileService;
	private @Value("${project.image}") String path;

	@ApiOperation(value = "This API is used to create post.", tags = "Post", authorizations = {
			@Authorization(value = "JWT") }, response = Response.class)
	@PostMapping("createPost/user/{userId}/category/{categoryId}")
	public ResponseEntity<PostWrapper> createPost(@Valid @RequestParam("post") String post,
			@PathVariable Integer userId, @PathVariable Integer categoryId,
			@RequestParam("image") MultipartFile image) {
		LOGGER.info(
				"Inside createPost in PostRestController method started with post: {},userId: {},categoryId: {},image: {}",
				post, userId, categoryId, image);
		PostWrapper postWrapper = new Gson().fromJson(post, PostWrapper.class);
		FileResponse uploadImage = fileService.uploadImage(path, image);
		postWrapper.setImage(uploadImage.getFileName());
		return new ResponseEntity<>(postService.createPost(postWrapper, userId, categoryId), HttpStatus.CREATED);
	}

	@ApiOperation(value = "This API is used to update post.", tags = "Post", authorizations = {
			@Authorization(value = "JWT") }, response = Response.class)
	@PutMapping("updatePost/{postId}")
	public ResponseEntity<PostWrapper> updatePost(@Valid @RequestParam("post") String post,
			@PathVariable("postId") Integer postId, @RequestParam("image") MultipartFile image) {
		PostWrapper postWrapper = new Gson().fromJson(post, PostWrapper.class);
		LOGGER.info("Inside updatePost in PostRestController method started with post: {},postId: {},image: {}", post,
				postId, image);
		FileResponse uploadImage = fileService.uploadImage(path, image);
		postWrapper.setImage(uploadImage.getFileName());
		return ResponseEntity.ok(postService.updatePost(postWrapper, postId));
	}

	@ApiOperation(value = "This API is used to delete post.", tags = "Post", authorizations = {
			@Authorization(value = "JWT") }, response = Response.class)
	@DeleteMapping("deletePost/{postId}")
	public ResponseEntity<Response> deletePost(@PathVariable("postId") Integer postId) {
		LOGGER.info("Inside deletePost in PostRestController method started with postId: {}", postId);
		postService.deletePost(postId);
		return new ResponseEntity<Response>(new Response("true", "Post deleted successfully", null), HttpStatus.OK);
	}

	@ApiOperation(value = "This API is used to get all posts.", tags = "Post", authorizations = {
			@Authorization(value = "JWT") }, response = Response.class)
	@GetMapping("getAllPosts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(name = "pageNumber", required = false, defaultValue = IConstants.PAGE_NUMBER) Integer pageNumber,
			@RequestParam(name = "pageSize", required = false, defaultValue = IConstants.PAGE_SIZE) Integer pageSize,
			@RequestParam(name = "sortBy", required = false, defaultValue = "postId") String sortBy) {
		LOGGER.info(
				"Inside getAllPosts in PostRestController method started with pageNumber: {},pageSize: {},sortBy: {}",
				pageNumber, pageSize, sortBy);
		return new ResponseEntity<PostResponse>(postService.getAllPost(pageNumber, pageSize, sortBy), HttpStatus.OK);
	}

	@ApiOperation(value = "This API is used to get post by postId.", tags = "Post", authorizations = {
			@Authorization(value = "JWT") }, response = Response.class)
	@GetMapping("getPostById/{postId}")
	public ResponseEntity<PostWrapper> getPostById(@PathVariable("postId") Integer postId) {
		LOGGER.info("Inside getPostById in PostRestController method started with postId: {}", postId);
		return ResponseEntity.ok(postService.getPostById(postId));
	}

	@ApiOperation(value = "This API is used to get post by userId.", tags = "Post", authorizations = {
			@Authorization(value = "JWT") }, response = Response.class)
	@GetMapping("getPostByUser/{userId}")
	public ResponseEntity<List<PostWrapper>> getPostByUser(@PathVariable("userId") Integer userId) {
		LOGGER.info("Inside getPostByUser in PostRestController method started with userId: {}", userId);
		return new ResponseEntity<List<PostWrapper>>(postService.findPostByUser(userId), HttpStatus.OK);
	}

	@ApiOperation(value = "This API is used to get post by categoryId.", tags = "Post", authorizations = {
			@Authorization(value = "JWT") }, response = Response.class)
	@GetMapping("getPostByCategory/{categoryId}")
	public ResponseEntity<List<PostWrapper>> getPostByCategory(@PathVariable("categoryId") Integer categoryId) {
		LOGGER.info("Inside getPostByCategory in PostRestController method started with categoryId: {}", categoryId);
		return new ResponseEntity<List<PostWrapper>>(postService.findPostByCategory(categoryId), HttpStatus.OK);
	}

	@ApiOperation(value = "This API is used to get search post", tags = "Post", authorizations = {
			@Authorization(value = "JWT") }, response = Response.class)
	@GetMapping("searchPost/{title}")
	public ResponseEntity<List<PostWrapper>> searchPost(@PathVariable("title") String title) {
		LOGGER.info("Inside searchPost in PostRestController method started with title: {}", title);
		return new ResponseEntity<List<PostWrapper>>(postService.searchPost(title), HttpStatus.OK);
	}

	@ApiOperation(value = "This API is used to upload post image.", tags = "Post", authorizations = {
			@Authorization(value = "JWT") }, response = Response.class)
	@PostMapping("uploadPostImage/{postId}")
	public ResponseEntity<PostWrapper> uploadPostImage(@RequestParam("image") MultipartFile image,
			@PathVariable("postId") Integer postId) {
		LOGGER.info("Inside uploadPostImage in PostRestController method started with image: {},image: {}", image,
				postId);
		PostWrapper postById = postService.getPostById(postId);
		FileResponse uploadImage = fileService.uploadImage(path, image);
		postById.setImage(uploadImage.getFileName());
		PostWrapper updatedPost = postService.updatePost(postById, postId);
		return new ResponseEntity<PostWrapper>(updatedPost, HttpStatus.OK);
	}

}
