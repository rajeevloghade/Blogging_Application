package com.blog.service;

import java.util.List;

import com.blog.wrapper.CommentWrapper;

public interface ICommentService {

	CommentWrapper createComment(CommentWrapper commentWrapper, Integer postId, Integer userId);

	CommentWrapper updateComment(CommentWrapper comment, Integer commentId);

	void deleteComment(Integer commentId);

	List<CommentWrapper> getCommentByPostId(Integer postId);

}
