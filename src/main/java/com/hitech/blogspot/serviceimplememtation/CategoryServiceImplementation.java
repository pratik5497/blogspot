package com.hitech.blogspot.serviceimplememtation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hitech.blogspot.customexceptions.ResourceNotFoundException;
import com.hitech.blogspot.dto.CategoryDto;
import com.hitech.blogspot.model.Category;
import com.hitech.blogspot.repository.CategoryRepository;
import com.hitech.blogspot.service.CategoryService;

@Service
public class CategoryServiceImplementation implements CategoryService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow((() -> new ResourceNotFoundException("Category", "Cotegory id", categoryId)));

		return modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> categoryList = this.categoryRepository.findAll();
		List<CategoryDto> categoryDtos = categoryList.stream()
				.map(((category) -> modelMapper.map(category, CategoryDto.class))).collect(Collectors.toList());
		return categoryDtos;
	}

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = modelMapper.map(categoryDto, Category.class);
		category = this.categoryRepository.save(category);
		return modelMapper.map(category, CategoryDto.class);

	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category id", categoryId));
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		return modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category id", categoryId));
		this.categoryRepository.delete(category);

	}

	@Override
	public void deleteAllCategories() {
		this.categoryRepository.deleteAll();

	}

}
