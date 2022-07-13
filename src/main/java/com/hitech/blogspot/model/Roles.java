package com.hitech.blogspot.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Roles {
	@Id
	private Integer roleId;
	private String roleName;
	@ManyToMany
	private Set<User> user;

}
