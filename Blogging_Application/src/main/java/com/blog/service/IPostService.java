package com.blog.service;

import java.util.List;

import com.blog.utils.PostResponse;
import com.blog.wrapper.PostWrapper;

public interface IPostService {

	PostWrapper createPost(PostWrapper post, Integer userId, Integer categoryId);

	PostWrapper updatePost(PostWrapper post, Integer postId);

	PostWrapper getPostById(Integer postId);

	List<PostWrapper> findPostByCategory(Integer categoryId);

	List<PostWrapper> findPostByUser(Integer userId);

	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy);

	void deletePost(Integer postId);

}
