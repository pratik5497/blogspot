package com.hitech.blogspot.controller;

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

import com.hitech.blogspot.dto.CategoryDto;
import com.hitech.blogspot.service.CategoryService;
import com.hitech.blogspot.util.ApiResponse;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getCategoryList() {
		List<CategoryDto> categoryList = this.categoryService.getAllCategories();
		return new ResponseEntity<List<CategoryDto>>(categoryList, HttpStatus.OK);
	}

	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable("categoryId") Integer categoryId) {
		CategoryDto categoryDto = this.categoryService.getCategoryById(categoryId);
		return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);

	}

	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") Integer categoryId) {
		this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted Successfully", true), HttpStatus.OK);

	}

	@DeleteMapping("/")
	public ResponseEntity<ApiResponse> deleteCategory() {
		this.categoryService.deleteAllCategories();
		return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted Successfully", true), HttpStatus.OK);

	}

	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		CategoryDto category = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(category, HttpStatus.CREATED);

	}

	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
			@PathVariable("categoryId") Integer categoryid) {
		CategoryDto category = this.categoryService.updateCategory(categoryDto, categoryid);
		return new ResponseEntity<CategoryDto>(category, HttpStatus.OK);

	}

}
