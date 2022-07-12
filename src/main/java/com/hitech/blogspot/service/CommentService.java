package com.hitech.blogspot.service;

import java.util.List;

import com.hitech.blogspot.dto.CommentDto;

public interface CommentService {
	CommentDto addComment(CommentDto commentDto, Integer userId, Integer postId);

	void deleteComment(Integer commentId);

	List<CommentDto> getCommentsByPost(Integer postId);

}
