package com.hitech.blogspot.service;

import java.util.List;

import com.hitech.blogspot.dto.PostDto;
import com.hitech.blogspot.util.PostResponse;

public interface PostService {
	PostDto getPost(Integer postId);

	PostResponse getAllPosts(Integer pageNo, Integer pageSize, String sortBy, String sortDirection);

	void deletePost(Integer postId);

	void deleteAllPosts();

	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

	PostDto updatePost(PostDto postDto, Integer postId);

	List<PostDto> getAllPostByUser(Integer userId);

	List<PostDto> getAllPostByCategory(Integer categoryId);

	List<PostDto> getMatchingPosts(String keyword);

}
