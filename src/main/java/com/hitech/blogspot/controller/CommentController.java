package com.hitech.blogspot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hitech.blogspot.dto.CommentDto;
import com.hitech.blogspot.service.CommentService;
import com.hitech.blogspot.util.ApiResponse;

@RestController
@RequestMapping("/api/")
public class CommentController {
	@Autowired
	private CommentService commentService;

	@PostMapping("/post/{postId}/user/{userId}/comment")
	public ResponseEntity<CommentDto> getComment(@RequestBody CommentDto commentDto,
			@PathVariable("userId") Integer userId, @PathVariable("postId") Integer postId) {
		CommentDto addComment = this.commentService.addComment(commentDto, userId, postId);

		return new ResponseEntity<CommentDto>(addComment, HttpStatus.CREATED);
	}

	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable("commentId") Integer commntId) {
		this.commentService.deleteComment(commntId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("The comment has successfully been deleted", true),
				HttpStatus.OK);

	}

	@GetMapping("/comment/{postId}")
	public ResponseEntity<List<CommentDto>> getComments(@PathVariable Integer postId) {
		List<CommentDto> commentList = this.commentService.getCommentsByPost(postId);
		return new ResponseEntity<List<CommentDto>>(commentList, HttpStatus.OK);
	}

}
