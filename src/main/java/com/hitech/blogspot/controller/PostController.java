package com.hitech.blogspot.controller;

import java.util.List;

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

import com.hitech.blogspot.dto.PostDto;
import com.hitech.blogspot.service.PostService;
import com.hitech.blogspot.util.ApiResponse;
import com.hitech.blogspot.util.PostResponse;

@RestController
@RequestMapping("/api/")
public class PostController {
	@Autowired
	private PostService postService;

	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPost(@PathVariable Integer postId) {
		PostDto post = this.postService.getPost(postId);
		return new ResponseEntity<PostDto>(post, HttpStatus.OK);

	}

	@GetMapping("/posts/")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "postId", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "5", required = false) String sortBy,
			@RequestParam(value = "sortDirection", defaultValue = "asc", required = false) String sortDirection) {
		PostResponse postResponse = this.postService.getAllPosts(pageNo, pageSize, sortBy, sortDirection);
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
	}

	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable("userId") Integer userId,
			@PathVariable("categoryId") Integer categoryId) {
		PostDto post = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(post, HttpStatus.CREATED);

	}

	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable("postId") Integer postId) {
		PostDto post = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(post, HttpStatus.OK);

	}

	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable("postId") Integer postId) {
		this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("The post has been deleted successfully", true),
				HttpStatus.OK);

	}

	@DeleteMapping("/posts/")
	public ResponseEntity<ApiResponse> deleteAllPosts() {
		this.postService.deleteAllPosts();
		return new ResponseEntity<ApiResponse>(new ApiResponse("The posts have been deleted successfuly", true),
				HttpStatus.OK);
	}

	@GetMapping("/user/{userId}/posts/")
	public ResponseEntity<List<PostDto>> getAllPostByUser(@PathVariable("userId") Integer userId) {
		List<PostDto> allPostByUser = this.postService.getAllPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(allPostByUser, HttpStatus.OK);

	}

	@GetMapping("/category/{categoryId}/posts/")
	public ResponseEntity<List<PostDto>> getAllPostByCategory(@PathVariable("categoryId") Integer categoryId) {
		List<PostDto> allPostByCategory = this.postService.getAllPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(allPostByCategory, HttpStatus.OK);

	}

	@GetMapping("/posts/search/")
	public ResponseEntity<List<PostDto>> searchPost(@RequestParam(value = "keyword", required = true) String keyword) {
		List<PostDto> matchingPosts = this.postService.getMatchingPosts(keyword);
		return new ResponseEntity<List<PostDto>>(matchingPosts, HttpStatus.OK);

	}

}
