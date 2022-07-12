package com.hitech.blogspot.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "post_Details")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postid;
	private String postTitle;
	@Column(length = 15000)
	private String postContent;
	private String postImage;

	private Date postDate;
	@ManyToOne
	@JsonManagedReference
	private User user;
	@ManyToOne
	@JsonManagedReference
	private Category category;

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Comment> commentList;

}
