package com.hitech.blogspot.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

	private Integer categoryId;
	@NotEmpty(message = "Category title must not be empty")
	@Size(min = 5, message = "Category title must be atlease 5 characters long")
	private String categoryTitle;
	@NotEmpty(message = "Category Description must not be empty")
	@Size(min = 5, message = "Category title must be atlease 5 characters long")
	private String categoryDescription;

}
