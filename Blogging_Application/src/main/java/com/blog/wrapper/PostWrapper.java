package com.blog.wrapper;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "Post Wrapper")
public class PostWrapper {

	private Integer postId;

	@NotEmpty
	@Size(min = 4, message = "must not be less than four character")
	private String title;

	@NotEmpty
	private String content;

	private String image;

	private Date postDate;

	private CategoryWrapper category;

	private UserWrapper user;

	private Set<CommentWrapper> comments;

	public CategoryWrapper getCategory() {
		return category;
	}

	public void setCategory(CategoryWrapper category) {
		this.category = category;
	}

	public UserWrapper getUser() {
		return user;
	}

	public void setUser(UserWrapper user) {
		this.user = user;
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public Set<CommentWrapper> getComments() {
		return comments;
	}

	public void setComments(Set<CommentWrapper> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "PostWrapper [postId=" + postId + ", title=" + title + ", content=" + content + ", image=" + image
				+ ", postDate=" + postDate + ", category=" + category + ", user=" + user + ", comments=" + comments
				+ "]";
	}

}
