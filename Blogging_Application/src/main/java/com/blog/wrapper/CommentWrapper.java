package com.blog.wrapper;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "Comments Wrapper")
public class CommentWrapper {

	private Integer commentId;

	private String content;

	private UserWrapper user;

	public UserWrapper getUser() {
		return user;
	}

	public void setUser(UserWrapper user) {
		this.user = user;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "CommentWrapper [commentId=" + commentId + ", content=" + content + ", user=" + user + "]";
	}

}
