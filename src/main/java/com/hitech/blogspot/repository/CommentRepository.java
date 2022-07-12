package com.hitech.blogspot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hitech.blogspot.model.Comment;
import com.hitech.blogspot.model.Post;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	List<Comment> findByPost(Post post);

}
