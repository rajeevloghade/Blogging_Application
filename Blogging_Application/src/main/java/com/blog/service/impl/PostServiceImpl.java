package com.blog.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.dao.ICategoryDao;
import com.blog.dao.IPostDao;
import com.blog.dao.IUserDao;
import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exception.ResourceNotFoundException;
import com.blog.service.IPostService;
import com.blog.utils.PostResponse;
import com.blog.wrapper.CategoryWrapper;
import com.blog.wrapper.PostWrapper;
import com.blog.wrapper.UserWrapper;

@Service
public class PostServiceImpl implements IPostService {

	private @Autowired IPostDao postDao;
	private @Autowired ModelMapper modelMapper;
	private @Autowired IUserDao userDao;
	private @Autowired ICategoryDao categoryDao;

	@Override
	public PostWrapper createPost(PostWrapper post, Integer userId, Integer categoryId) {
		User userById = userDao.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		Category categoryById = categoryDao.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		post.setPostDate(new Date());
		post.setUser(modelMapper.map(userById, UserWrapper.class));
		post.setCategory(modelMapper.map(categoryById, CategoryWrapper.class));
		return entityToWrapper(postDao.save(wrapperToEntity(post)));
	}

	@Override
	public PostWrapper updatePost(PostWrapper postWrapper, Integer postId) {
		Post postById = postDao.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
		postById.setTitle(postWrapper.getTitle());
		postById.setContent(postWrapper.getContent());
		postById.setImage(postWrapper.getImage());
		postById.setPostDate(new Date());
		return entityToWrapper(postDao.save(postById));
	}

	@Override
	public PostWrapper getPostById(Integer postId) {
		return entityToWrapper(
				postDao.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId)));
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy) {
		PostResponse postResponse = new PostResponse();
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
		Page<Post> pagePosts = postDao.findAll(pageable);
		List<Post> allPosts = pagePosts.getContent();
		postResponse.setPosts(allPosts.stream().map(post -> entityToWrapper(post)).collect(Collectors.toList()));
		postResponse.setPageNumber(pagePosts.getNumber());
		postResponse.setPageSize(pagePosts.getSize());
		postResponse.setTotalElements(pagePosts.getTotalElements());
		postResponse.setTotalPages(pagePosts.getTotalPages());
		postResponse.setLastPage(pagePosts.isLast());
		return postResponse;
	}

	@Override
	public void deletePost(Integer postId) {
		postDao.delete(
				postDao.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId)));
	}

	@Override
	public List<PostWrapper> findPostByUser(Integer userId) {
		User userById = userDao.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		return postDao.findPostByUser(userById).stream().map(post -> entityToWrapper(post))
				.collect(Collectors.toList());
	}

	@Override
	public List<PostWrapper> findPostByCategory(Integer categoryId) {
		Category categoryById = categoryDao.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("categoryId", "categoryId", categoryId));
		return postDao.findPostByCategory(categoryById).stream().map(post -> entityToWrapper(post))
				.collect(Collectors.toList());
	}

	@Override
	public List<PostWrapper> searchPost(String title) {
		return postDao.findByTitleContaining(title).stream().map(post -> entityToWrapper(post))
				.collect(Collectors.toList());
	}

	public Post wrapperToEntity(PostWrapper postWrapper) {
		return modelMapper.map(postWrapper, Post.class);
	}

	public PostWrapper entityToWrapper(Post post) {
		return modelMapper.map(post, PostWrapper.class);
	}

}