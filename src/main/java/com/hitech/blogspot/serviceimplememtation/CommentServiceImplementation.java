package com.hitech.blogspot.serviceimplememtation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hitech.blogspot.customexceptions.ResourceNotFoundException;
import com.hitech.blogspot.dto.CommentDto;
import com.hitech.blogspot.model.Comment;
import com.hitech.blogspot.model.Post;
import com.hitech.blogspot.model.User;
import com.hitech.blogspot.repository.CommentRepository;
import com.hitech.blogspot.repository.PostRepository;
import com.hitech.blogspot.repository.UserRepository;
import com.hitech.blogspot.service.CommentService;

@Service
public class CommentServiceImplementation implements CommentService {
	@Autowired
	private CommentRepository commentRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PostRepository postRepo;

	@Override
	public CommentDto addComment(CommentDto commentDto, Integer userId, Integer postId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setUser(user);
		comment.setPost(post);

		Comment updated = this.commentRepo.save(comment);

		return this.modelMapper.map(updated, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment = this.commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "Comment Id", commentId));
		this.commentRepo.delete(comment);

	}

	@Override
	public List<CommentDto> getCommentsByPost(Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
		List<Comment> commentList = this.commentRepo.findByPost(post);
		List<CommentDto> collect = commentList.stream().map((comment) -> modelMapper.map(comment, CommentDto.class))
				.collect(Collectors.toList());
		return collect;
	}

}
