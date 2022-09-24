package com.blog.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Post")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "postid")
	private Integer postId;

	@Column(name = "title")
	private String title;

	@Column(name = "content", length = 10000)
	private String content;

	@Column(name = "imagename")
	private String image;

	@Column(name = "postdate")
	private Date postDate;

	@ManyToOne
	@JoinColumn(name = "categoryid_fk", referencedColumnName = "categoryId", nullable = false)
	private Category category;

	@ManyToOne
	@JoinColumn(name = "userid_fk", referencedColumnName = "userId", nullable = false)
	private User user;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
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

	@Override
	public String toString() {
		return "Post [postId=" + postId + ", title=" + title + ", content=" + content + ", image=" + image
				+ ", postDate=" + postDate + ", category=" + category + ", user=" + user + "]";
	}

}
