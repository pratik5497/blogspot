package com.hitech.blogspot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hitech.blogspot.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUserEmailId(String emailId);

}
