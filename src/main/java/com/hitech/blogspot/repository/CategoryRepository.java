package com.hitech.blogspot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hitech.blogspot.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
