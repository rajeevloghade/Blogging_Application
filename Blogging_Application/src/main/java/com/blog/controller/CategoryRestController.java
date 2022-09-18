package com.blog.controller;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;

import com.blog.service.ICategoryService;
import com.blog.utils.Response;
import com.blog.wrapper.CategoryWrapper;

@RestController
@RequestMapping("api/category")
public class CategoryRestController {

	private @Autowired ICategoryService categoryService;

	@PostMapping("createCategory")
	public ResponseEntity<CategoryWrapper> createCategory(@Valid @RequestBody CategoryWrapper categoryWrapper) {
		return new ResponseEntity<>(categoryService.createCategory(categoryWrapper), HttpStatus.CREATED);
	}

	@PutMapping("updateCategory/{categoryId}")
	public ResponseEntity<CategoryWrapper> updateCategory(@Valid @RequestBody CategoryWrapper categoryWrapper,
			@PathVariable("categoryId") Integer categoryId) {
		return ResponseEntity.ok(categoryService.updateCategory(categoryWrapper, categoryId));
	}

	@DeleteMapping("deleteCategory/{categoryId}")
	public ResponseEntity<Response> deleteCategory(@PathVariable("categoryId") Integer categoryId) {
		categoryService.deleteCategory(categoryId);
		return new ResponseEntity<Response>(new Response("true", "Category deleted successfully", null), HttpStatus.OK);
	}

	@GetMapping("getAllCategories")
	public ResponseEntity<List<CategoryWrapper>> getAllCategory() {
		return ResponseEntity.ok(categoryService.getAllCategory());
	}

	@GetMapping("getCategoryById/{categoryId}")
	public ResponseEntity<CategoryWrapper> getCategoryById(@PathVariable("categoryId") Integer categoryId) {
		return ResponseEntity.ok(categoryService.getCategoryById(categoryId));
	}

}
