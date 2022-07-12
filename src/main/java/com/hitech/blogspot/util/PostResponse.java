package com.hitech.blogspot.util;

import java.util.List;

import com.hitech.blogspot.dto.PostDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostResponse {
	private Integer pageNo;
	private Integer pageSize;
	private List<PostDto> pageContent;
	private Long totalElement;
	private Integer totalPages;
	private boolean isLastPage;

}
