package com.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dao.ICategoryDao;
import com.blog.entities.Category;
import com.blog.exception.ResourceNotFoundException;
import com.blog.service.ICategoryService;
import com.blog.wrapper.CategoryWrapper;

@Service
public class CategoryServiceImpl implements ICategoryService {

	private @Autowired ICategoryDao categoryDao;
	private @Autowired ModelMapper modelMapper;

	@Override
	public CategoryWrapper createCategory(CategoryWrapper category) {
		return entityToWrapper(categoryDao.save(wrapperToEntity(category)));
	}

	@Override
	public CategoryWrapper updateCategory(CategoryWrapper categoryWrapper, Integer categoryId) {
		Category categoryById = categoryDao.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		categoryById.setCategoryName(categoryWrapper.getCategoryName());
		categoryById.setCategoryDescription(categoryWrapper.getCategoryDescription());
		return entityToWrapper(categoryDao.save(categoryById));
	}

	@Override
	public CategoryWrapper getCategoryById(Integer categoryId) {
		return entityToWrapper(categoryDao.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId)));
	}

	@Override
	public List<CategoryWrapper> getAllCategory() {
		return categoryDao.findAll().stream().map(category -> entityToWrapper(category)).collect(Collectors.toList());
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		categoryDao.delete(categoryDao.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId)));
	}

	public Category wrapperToEntity(CategoryWrapper categoryWrapper) {
		return modelMapper.map(categoryWrapper, Category.class);
	}

	public CategoryWrapper entityToWrapper(Category category) {
		return modelMapper.map(category, CategoryWrapper.class);
	}

}
