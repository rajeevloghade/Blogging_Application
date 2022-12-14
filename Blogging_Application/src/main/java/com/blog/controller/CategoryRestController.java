package com.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.service.ICategoryService;
import com.blog.utils.IConstants;
import com.blog.utils.Response;
import com.blog.wrapper.CategoryWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@Api(tags = "Category", description = "Provides Category APIs")
@RestController
@RequestMapping("api/category")
public class CategoryRestController {

	private static final Logger LOGGER = LogManager.getLogger(CategoryRestController.class);
	private static final Logger EMAIL = LogManager.getLogger("EMAIL");

	private @Autowired ICategoryService categoryService;

	@ApiOperation(value = "This API is used to create category.", tags = "Category", authorizations = {
			@Authorization(value = "JWT") }, response = Response.class)
	@PostMapping("createCategory")
	public ResponseEntity<CategoryWrapper> createCategory(@Valid @RequestBody CategoryWrapper categoryWrapper) {
		LOGGER.info("Inside createCategory in CategoryRestController method started with categoryWrapper: {}",
				categoryWrapper);
		return new ResponseEntity<>(categoryService.createCategory(categoryWrapper), HttpStatus.CREATED);
	}

	@ApiOperation(value = "This API is used to update category.", tags = "Category", authorizations = {
			@Authorization(value = "JWT") }, response = Response.class)
	@PutMapping("updateCategory/{categoryId}")
	public ResponseEntity<CategoryWrapper> updateCategory(@Valid @RequestBody CategoryWrapper categoryWrapper,
			@PathVariable("categoryId") Integer categoryId) {
		LOGGER.info(
				"Inside createCategory in CategoryRestController method started with categoryWrapper: {},categoryId: {}",
				categoryWrapper, categoryId);
		return ResponseEntity.ok(categoryService.updateCategory(categoryWrapper, categoryId));
	}

	@ApiOperation(value = "This API is used to delete category.", tags = "Category", authorizations = {
			@Authorization(value = "JWT") }, response = Response.class)
	@DeleteMapping("deleteCategory/{categoryId}")
	public ResponseEntity<Response> deleteCategory(@PathVariable("categoryId") Integer categoryId) {
		LOGGER.info("Inside deleteCategory in CategoryRestController method started with categoryId: {}", categoryId);
		categoryService.deleteCategory(categoryId);
		return new ResponseEntity<Response>(new Response("true", "Category deleted successfully", null), HttpStatus.OK);
	}

	@ApiOperation(value = "This API is used to get all categories.", tags = "Category", authorizations = {
			@Authorization(value = "JWT") }, response = Response.class)
	@GetMapping("getAllCategories")
	public ResponseEntity<List<CategoryWrapper>> getAllCategory(
			@RequestParam(name = "pageNumber", required = false, defaultValue = IConstants.PAGE_NUMBER) Integer pageNumber,
			@RequestParam(name = "pageSize", required = false, defaultValue = IConstants.PAGE_SIZE) Integer pageSize,
			@RequestParam(name = "sortBy", required = false, defaultValue = "categoryId") String sortBy) {
		LOGGER.info(
				"Inside getAllPosts in CategoryRestController method started with pageNumber: {},pageSize: {},sortBy: {}",
				pageNumber, pageSize, sortBy);
		return ResponseEntity.ok(categoryService.getAllCategory(pageNumber, pageSize, sortBy));
	}

	@ApiOperation(value = "This API is used to get category by categoryId.", tags = "Category", authorizations = {
			@Authorization(value = "JWT") }, response = Response.class)
	@GetMapping("getCategoryById/{categoryId}")
	public ResponseEntity<CategoryWrapper> getCategoryById(@PathVariable("categoryId") Integer categoryId) {
		LOGGER.info("Inside getCategoryById in CategoryRestController method started with categoryId: {}", categoryId);
		return ResponseEntity.ok(categoryService.getCategoryById(categoryId));
	}

	@ApiOperation(value = "This API is used to search category.", tags = "Category", authorizations = {
			@Authorization(value = "JWT") }, response = Response.class)
	@GetMapping("searchCategory/{categoryName}")
	public ResponseEntity<List<CategoryWrapper>> searchCategory(@PathVariable("categoryName") String categoryName) {
		LOGGER.info("Inside searchCategory in CategoryRestController method started with categoryName: {}",
				categoryName);
		return ResponseEntity.ok(categoryService.searchCategory(categoryName));
	}

}
