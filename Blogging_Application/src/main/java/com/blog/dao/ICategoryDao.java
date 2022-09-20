package com.blog.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entities.Category;
import com.blog.wrapper.CategoryWrapper;

public interface ICategoryDao extends JpaRepository<Category, Integer> {

	List<Category> findByCategoryNameContaining(String categoryName);

}
