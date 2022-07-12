package com.hitech.blogspot.serviceimplememtation;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hitech.blogspot.customexceptions.ResourceNotFoundException;
import com.hitech.blogspot.dto.PostDto;
import com.hitech.blogspot.model.Category;
import com.hitech.blogspot.model.Post;
import com.hitech.blogspot.model.User;
import com.hitech.blogspot.repository.CategoryRepository;
import com.hitech.blogspot.repository.PostRepository;
import com.hitech.blogspot.repository.UserRepository;
import com.hitech.blogspot.service.PostService;
import com.hitech.blogspot.util.PostResponse;

@Service
public class PostServiceImplementation implements PostService {
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDto getPost(Integer postId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostResponse getAllPosts(Integer pageNo, Integer pageSize, String sortBy, String sortDirection) {

		Sort sort = (sortDirection.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
		Pageable postPage = PageRequest.of(pageNo, pageSize, sort);

		Page<Post> pages = this.postRepository.findAll(postPage);
		List<Post> allPostsList = pages.getContent();
		List<PostDto> allPostDtoList = allPostsList.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setPageContent(allPostDtoList);
		postResponse.setPageNo(pages.getNumber());
		postResponse.setPageSize(pages.getSize());
		postResponse.setTotalElement(pages.getTotalElements());
		postResponse.setTotalPages(pages.getTotalPages());
		postResponse.setLastPage(pages.isLast());
		return postResponse;
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
		this.postRepository.delete(post);

	}

	@Override
	public void deleteAllPosts() {
		this.postRepository.deleteAll();

	}

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User id", userId));
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category id", categoryId));
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setUser(user);
		post.setCategory(category);
		post.setPostDate(new Date());
		post.setPostImage("default.jpg");
		Post save = this.postRepository.save(post);
		return modelMapper.map(save, PostDto.class);

	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
		post.setPostTitle(postDto.getPostTitle());
		post.setPostContent(postDto.getPostContent());
		post.setPostDate(new Date());
		Post saved = this.postRepository.save(post);
		return this.modelMapper.map(saved, PostDto.class);
	}

	@Override
	public List<PostDto> getAllPostByUser(Integer userId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));
		List<Post> postList = this.postRepository.findByUser(user);
		List<PostDto> postDtoList = null;
		if (!postList.isEmpty()) {
			postDtoList = postList.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
					.collect(Collectors.toList());
		} else {
			throw new ResourceNotFoundException("Posts", "user Id", userId);
		}
		return postDtoList;
	}

	@Override
	public List<PostDto> getAllPostByCategory(Integer categoryId) {
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category id", categoryId));
		List<Post> postList = this.postRepository.findByCategory(category);
		List<PostDto> postDtoList = null;
		if (!postList.isEmpty()) {
			postDtoList = postList.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
					.collect(Collectors.toList());
		} else {
			throw new ResourceNotFoundException("Posts", "Category Id", categoryId);
		}

		return postDtoList;
	}

	@Override
	public List<PostDto> getMatchingPosts(String keyword) {
		List<Post> postList = this.postRepository.findByPostTitleContaining(keyword);
		List<PostDto> postDtoList = null;
		if (!postList.isEmpty()) {
			postDtoList = postList.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
					.collect(Collectors.toList());

		} else {
			throw new ResourceNotFoundException("Post", "Keyword", keyword);
		}

		return postDtoList;
	}

}
