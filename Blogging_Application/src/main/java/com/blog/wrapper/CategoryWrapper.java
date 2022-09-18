package com.blog.wrapper;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CategoryWrapper {

	private Integer categoryId;

	@NotEmpty
	@Size(min = 4, message = "must not be less than four character")
	private String categoryName;

	@NotEmpty
	private String categoryDescription;

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	@Override
	public String toString() {
		return "CategoryWrapper [categoryId=" + categoryId + ", categoryName=" + categoryName + ", categoryDescription="
				+ categoryDescription + "]";
	}

}
