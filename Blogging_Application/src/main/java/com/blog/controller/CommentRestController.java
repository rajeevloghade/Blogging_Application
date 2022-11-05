package com.blog.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.blog.service.ICommentService;
import com.blog.utils.Response;
import com.blog.wrapper.CommentWrapper;

@RestController
@RequestMapping("api/comment")
public class CommentRestController {

	private static final Logger LOGGER = LogManager.getLogger(CommentRestController.class);
	private static final Logger EMAIL = LogManager.getLogger("EMAIL");

	private @Autowired ICommentService commentService;

	@PostMapping("createComment/post/{postId}/user/{userId}")
	public ResponseEntity<CommentWrapper> createComment(@RequestBody CommentWrapper commentWrapper,
			@PathVariable Integer postId, @PathVariable Integer userId) {
		LOGGER.info(
				"Inside createComment in CommentRestController method started with commentWrapper: {},userId: {},postId: {}",
				commentWrapper, userId, postId);
		return new ResponseEntity<CommentWrapper>(commentService.createComment(commentWrapper, postId, userId),
				HttpStatus.CREATED);
	}

	@DeleteMapping("deleteComment/{commentId}")
	public ResponseEntity<Response> deleteComment(@PathVariable Integer commentId) {
		LOGGER.info("Inside deleteComment in CommentRestController method started with commentId: {}", commentId);
		commentService.deleteComment(commentId);
		return new ResponseEntity<Response>(new Response("true", "Comment deleted successfully", null), HttpStatus.OK);
	}

	@PutMapping("updateComment/{commentId}")
	public ResponseEntity<CommentWrapper> updateComment(@RequestBody CommentWrapper commentWrapper,
			@PathVariable Integer commentId) {
		LOGGER.info(
				"Inside updateComment in CommentRestController method started with commentWrapper: {},commentId: {}",
				commentWrapper, commentId);
		return ResponseEntity.ok(commentService.updateComment(commentWrapper, commentId));
	}

	@GetMapping("getCommentByPostId/{postId}")
	public ResponseEntity<List<CommentWrapper>> getCommentByPostId(@PathVariable Integer postId) {
		LOGGER.info("Inside getCommentByPostId in CommentRestController method started with postId: {}", postId);
		return new ResponseEntity<List<CommentWrapper>>(commentService.getCommentByPostId(postId), HttpStatus.OK);
	}
}
