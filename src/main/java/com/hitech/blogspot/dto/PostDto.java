package com.hitech.blogspot.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.hitech.blogspot.model.Category;
import com.hitech.blogspot.model.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class PostDto {

	private Integer postid;
	@NotEmpty(message = "Post title should not be empty")
	@Size(min = 12, message = "Post title should atleast be 12 characters long")
	private String postTitle;
	@NotEmpty(message = "Post Content should not be empty")
	@Size(min = 300, max = 15000, message = "Post title should atleast be 300 characters long and maximum be 15000 characters long")
	private String postContent;
	private String postImage;
	private Date postDate;
	private User user;
	private Category category;
	private List<CommentDto> commentList = new ArrayList<>();

}
