package com.blog.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entities.Comment;
import com.blog.entities.Post;

public interface ICommentDao extends JpaRepository<Comment, Integer> {

	public List<Comment> findCommentByPost(Post post);
}
