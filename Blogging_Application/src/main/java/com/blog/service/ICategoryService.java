package com.blog.service;

import java.util.List;

import com.blog.wrapper.CategoryWrapper;

public interface ICategoryService {

	CategoryWrapper createCategory(CategoryWrapper category);

	CategoryWrapper updateCategory(CategoryWrapper category, Integer userId);

	CategoryWrapper getCategoryById(Integer categoryId);

	List<CategoryWrapper> getAllCategory();

	void deleteCategory(Integer categoryId);
}
