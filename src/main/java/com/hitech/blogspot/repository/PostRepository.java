package com.hitech.blogspot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hitech.blogspot.model.Category;
import com.hitech.blogspot.model.Post;
import com.hitech.blogspot.model.User;

public interface PostRepository extends JpaRepository<Post, Integer> {
	List<Post> findByUser(User user);

	List<Post> findByCategory(Category category);

	List<Post> findByPostTitleContaining(String keyword);

}
