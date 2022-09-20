package com.blog.controller;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;

import com.blog.service.IPostService;
import com.blog.utils.Response;
import com.blog.wrapper.PostWrapper;

@RestController
@RequestMapping("api/post")
public class PostRestController {

	private @Autowired IPostService postService;

	@PostMapping("createPost/user/{userId}/category/{categoryId}")
	public ResponseEntity<PostWrapper> createPost(@Valid @RequestBody PostWrapper postWrapper,
			@PathVariable Integer userId, @PathVariable Integer categoryId) {
		return new ResponseEntity<>(postService.createPost(postWrapper, userId, categoryId), HttpStatus.CREATED);
	}

	@PutMapping("updateCategory/{categoryId}")
	public ResponseEntity<PostWrapper> updatePost(@Valid @RequestBody PostWrapper categoryWrapper,
			@PathVariable("categoryId") Integer categoryId) {
		return ResponseEntity.ok(postService.updatePost(categoryWrapper, categoryId));
	}

	@DeleteMapping("deletePost/{postId}")
	public ResponseEntity<Response> deletePost(@PathVariable("categoryId") Integer postId) {
		postService.deletePost(postId);
		return new ResponseEntity<Response>(new Response("true", "Post deleted successfully", null), HttpStatus.OK);
	}

	@GetMapping("getAllPosts")
	public ResponseEntity<List<PostWrapper>> getAllPosts() {
		return ResponseEntity.ok(postService.getAllPost());
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

}
