package com.blog.controller;

import java.util.List;

import javax.validation.Valid;

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

@RestController
@RequestMapping("api/post")
public class PostRestController {

	private @Autowired IPostService postService;
	private @Autowired IFileService fileService;
	private @Value("${project.image}") String path;

	@PostMapping("createPost/user/{userId}/category/{categoryId}")
	public ResponseEntity<PostWrapper> createPost(@Valid @RequestParam("post") String post,
			@PathVariable Integer userId, @PathVariable Integer categoryId,
			@RequestParam("image") MultipartFile image) {
		PostWrapper postWrapper = new Gson().fromJson(post, PostWrapper.class);
		FileResponse uploadImage = fileService.uploadImage(path, image);
		postWrapper.setImage(uploadImage.getFileName());
		return new ResponseEntity<>(postService.createPost(postWrapper, userId, categoryId), HttpStatus.CREATED);
	}

	@PutMapping("updatePost/{postId}")
	public ResponseEntity<PostWrapper> updatePost(@Valid @RequestParam("post") String post,
			@PathVariable("postId") Integer postId, @RequestParam("image") MultipartFile image) {
		PostWrapper postWrapper = new Gson().fromJson(post, PostWrapper.class);
		FileResponse uploadImage = fileService.uploadImage(path, image);
		postWrapper.setImage(uploadImage.getFileName());
		return ResponseEntity.ok(postService.updatePost(postWrapper, postId));
	}

	@DeleteMapping("deletePost/{postId}")
	public ResponseEntity<Response> deletePost(@PathVariable("postId") Integer postId) {
		postService.deletePost(postId);
		return new ResponseEntity<Response>(new Response("true", "Post deleted successfully", null), HttpStatus.OK);
	}

	@GetMapping("getAllPosts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(name = "pageNumber", required = false, defaultValue = IConstants.PAGE_NUMBER) Integer pageNumber,
			@RequestParam(name = "pageSize", required = false, defaultValue = IConstants.PAGE_SIZE) Integer pageSize,
			@RequestParam(name = "sortBy", required = false, defaultValue = "postId") String sortBy) {
		return new ResponseEntity<PostResponse>(postService.getAllPost(pageNumber, pageSize, sortBy), HttpStatus.OK);
	}

	@GetMapping("getPostById/{postId}")
	public ResponseEntity<PostWrapper> getPostById(@PathVariable("postId") Integer postId) {
		return ResponseEntity.ok(postService.getPostById(postId));
	}

	@GetMapping("getPostByUser/{userId}")
	public ResponseEntity<List<PostWrapper>> getPostByUser(@PathVariable("userId") Integer userId) {
		return new ResponseEntity<List<PostWrapper>>(postService.findPostByUser(userId), HttpStatus.OK);
	}

	@GetMapping("getPostByCategory/{categoryId}")
	public ResponseEntity<List<PostWrapper>> getPostByCategory(@PathVariable("categoryId") Integer categoryId) {
		return new ResponseEntity<List<PostWrapper>>(postService.findPostByCategory(categoryId), HttpStatus.OK);
	}

	@GetMapping("searchPost/{title}")
	public ResponseEntity<List<PostWrapper>> searchPost(@PathVariable("title") String title) {
		return new ResponseEntity<List<PostWrapper>>(postService.searchPost(title), HttpStatus.OK);
	}

	@PostMapping("uploadPostImage/{postId}")
	public ResponseEntity<PostWrapper> uploadPostImage(@RequestParam("image") MultipartFile image,
			@PathVariable("postId") Integer postId) {
		PostWrapper postById = postService.getPostById(postId);
		FileResponse uploadImage = fileService.uploadImage(path, image);
		postById.setImage(uploadImage.getFileName());
		PostWrapper updatedPost = postService.updatePost(postById, postId);
		return new ResponseEntity<PostWrapper>(updatedPost, HttpStatus.OK);
	}

}
