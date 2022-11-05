package com.blog.controller;

import java.util.List;

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

	private @Autowired ICommentService commentService;

	@PostMapping("createComment/post/{postId}/user/{userId}")
	public ResponseEntity<CommentWrapper> createComment(@RequestBody CommentWrapper commentWrapper,
			@PathVariable Integer postId, @PathVariable Integer userId) {
		return new ResponseEntity<CommentWrapper>(commentService.createComment(commentWrapper, postId, userId),
				HttpStatus.CREATED);
	}

	@DeleteMapping("deleteComment/{commentId}")
	public ResponseEntity<Response> deleteComment(@PathVariable Integer commentId) {
		commentService.deleteComment(commentId);
		return new ResponseEntity<Response>(new Response("true", "Comment deleted successfully", null), HttpStatus.OK);
	}

	@PutMapping("updateComment/{commentId}")
	public ResponseEntity<CommentWrapper> updateComment(@RequestBody CommentWrapper commentWrapper,
			@PathVariable Integer commentId) {
		return ResponseEntity.ok(commentService.updateComment(commentWrapper, commentId));
	}

	@GetMapping("getCommentByPostId/{postId}")
	public ResponseEntity<List<CommentWrapper>> getCommentByPostId(@PathVariable Integer postId) {
		return new ResponseEntity<List<CommentWrapper>>(commentService.getCommentByPostId(postId), HttpStatus.OK);
	}
}
