package com.blog.service;

import java.util.List;

import com.blog.wrapper.CategoryWrapper;

public interface ICategoryService {

	CategoryWrapper createCategory(CategoryWrapper category);

	CategoryWrapper updateCategory(CategoryWrapper category, Integer userId);

	CategoryWrapper getCategoryById(Integer categoryId);

	void deleteCategory(Integer categoryId);

	List<CategoryWrapper> getAllCategory(Integer pageNumber, Integer pageSize, String sortBy);

	List<CategoryWrapper> searchCategory(String categoryName);
}
