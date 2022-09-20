package com.blog.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;

public interface IPostDao extends JpaRepository<Post, Integer> {

	List<Post> findPostByCategory(Category category);

	List<Post> findPostByUser(User user);

	List<Post> findByTitleContaining(String title);

}
