package com.hitech.blogspot.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class CommentDto {

	private Integer commentId;
	@NotEmpty(message = "Comment cannot be blank")
	private String commentContext;
	// private User user;
	// private Post post;

}
