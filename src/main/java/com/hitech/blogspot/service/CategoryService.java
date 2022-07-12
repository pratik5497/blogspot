package com.hitech.blogspot.service;

import java.util.List;

import com.hitech.blogspot.dto.CategoryDto;

public interface CategoryService {
	// get specific category
	CategoryDto getCategoryById(Integer categoryId);

	// get all categories
	List<CategoryDto> getAllCategories();

	// create category
	CategoryDto createCategory(CategoryDto categoryDto);

	// update category
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

	// delete specific category
	void deleteCategory(Integer categoryId);

	// delete all categories
	void deleteAllCategories();

}
