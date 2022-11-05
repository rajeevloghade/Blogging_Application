package com.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.dao.ICategoryDao;
import com.blog.entities.Category;
import com.blog.exception.ResourceNotFoundException;
import com.blog.service.ICategoryService;
import com.blog.wrapper.CategoryWrapper;

@Service
public class CategoryServiceImpl implements ICategoryService {

	private static final Logger LOGGER = LogManager.getLogger(CategoryServiceImpl.class);
	private static final Logger EMAIL = LogManager.getLogger("EMAIL");

	private @Autowired ICategoryDao categoryDao;
	private @Autowired ModelMapper modelMapper;

	@Override
	public CategoryWrapper createCategory(CategoryWrapper category) {
		LOGGER.info("Inside createCategory in CategoryServiceImpl method started with categoryWrapper: {}", category);
		return entityToWrapper(categoryDao.save(wrapperToEntity(category)));
	}

	@Override
	public CategoryWrapper updateCategory(CategoryWrapper categoryWrapper, Integer categoryId) {
		LOGGER.info(
				"Inside createCategory in CategoryServiceImpl method started with categoryWrapper: {},categoryId: {}",
				categoryWrapper, categoryId);
		Category categoryById = categoryDao.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		categoryById.setCategoryName(categoryWrapper.getCategoryName());
		categoryById.setCategoryDescription(categoryWrapper.getCategoryDescription());
		return entityToWrapper(categoryDao.save(categoryById));
	}

	@Override
	public CategoryWrapper getCategoryById(Integer categoryId) {
		LOGGER.info("Inside getCategoryById in CategoryServiceImpl method started with categoryId: {}", categoryId);
		return entityToWrapper(categoryDao.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId)));
	}

	@Override
	public List<CategoryWrapper> getAllCategory(Integer pageNumber, Integer pageSize, String sortBy) {
		LOGGER.info(
				"Inside getAllPosts in CategoryServiceImpl method started with pageNumber: {},pageSize: {},sortBy: {}",
				pageNumber, pageSize, sortBy);
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
		Page<Category> pagePosts = categoryDao.findAll(pageable);
		List<Category> allCategories = pagePosts.getContent();
		return allCategories.stream().map(category -> entityToWrapper(category)).collect(Collectors.toList());
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		LOGGER.info("Inside deleteCategory in CategoryServiceImpl method started with categoryId: {}", categoryId);
		categoryDao.delete(categoryDao.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId)));
	}

	@Override
	public List<CategoryWrapper> searchCategory(String categoryName) {
		LOGGER.info("Inside searchCategory in CategoryServiceImpl method started with categoryName: {}", categoryName);
		return categoryDao.findByCategoryNameContaining(categoryName).stream()
				.map(category -> entityToWrapper(category)).collect(Collectors.toList());
	}

	public Category wrapperToEntity(CategoryWrapper categoryWrapper) {
		return modelMapper.map(categoryWrapper, Category.class);
	}

	public CategoryWrapper entityToWrapper(Category category) {
		return modelMapper.map(category, CategoryWrapper.class);
	}

}
