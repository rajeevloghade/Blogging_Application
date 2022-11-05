package com.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dao.ICommentDao;
import com.blog.dao.IPostDao;
import com.blog.dao.IUserDao;
import com.blog.entities.Comment;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exception.ResourceNotFoundException;
import com.blog.service.ICommentService;
import com.blog.wrapper.CommentWrapper;

@Service
public class CommentServiceImpl implements ICommentService {

	private static final Logger LOGGER = LogManager.getLogger(CommentServiceImpl.class);
	private static final Logger EMAIL = LogManager.getLogger("EMAIL");

	private @Autowired ICommentDao commentDao;
	private @Autowired IPostDao postDao;
	private @Autowired IUserDao userDao;
	private @Autowired ModelMapper modelMapper;

	@Override
	public CommentWrapper createComment(CommentWrapper commentWrapper, Integer postId, Integer userId) {
		LOGGER.info(
				"Inside createComment in CommentServiceImpl method started with commentWrapper: {},userId: {},postId: {}",
				commentWrapper, userId, postId);
		Post postById = postDao.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
		User userById = userDao.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		Comment comment = wrapperToEntity(commentWrapper);
		comment.setPost(postById);
		comment.setUser(userById);
		return entityToWrapper(commentDao.save(comment));
	}

	@Override
	public CommentWrapper updateComment(CommentWrapper commentWrapper, Integer commentId) {
		LOGGER.info("Inside updateComment in CommentServiceImpl method started with commentWrapper: {},commentId: {}",
				commentWrapper, commentId);
		Comment commentById = commentDao.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId));
		commentById.setContent(commentWrapper.getContent());
		return entityToWrapper(commentDao.save(commentById));
	}

	@Override
	public void deleteComment(Integer commentId) {
		LOGGER.info("Inside deleteComment in CommentServiceImpl method started with commentId: {}", commentId);
		Comment commentById = commentDao.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId));
		commentDao.delete(commentById);
	}

	@Override
	public List<CommentWrapper> getCommentByPostId(Integer postId) {
		LOGGER.info("Inside getCommentByPostId in CommentServiceImpl method started with postId: {}", postId);
		Post postById = postDao.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
		return commentDao.findCommentByPost(postById).stream().map(comment -> entityToWrapper(comment))
				.collect(Collectors.toList());
	}

	public Comment wrapperToEntity(CommentWrapper commentWrapper) {
		return modelMapper.map(commentWrapper, Comment.class);
	}

	public CommentWrapper entityToWrapper(Comment comment) {
		return modelMapper.map(comment, CommentWrapper.class);
	}

}
