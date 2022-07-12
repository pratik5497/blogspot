package com.hitech.blogspot.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hitech.blogspot.dto.UserDto;
import com.hitech.blogspot.service.UserService;
import com.hitech.blogspot.util.ApiResponse;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserService userService;
	Logger log = LogManager.getLogger(UserController.class);

	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUser() {
		List<UserDto> allUsers = userService.getAllUsers();
		if (!allUsers.isEmpty()) {
			return ResponseEntity.ok().body(allUsers);

		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUser(@PathVariable("userId") Integer userid) {
		UserDto user = userService.getUserbyId(userid);

		return ResponseEntity.status(HttpStatus.OK).body(user);

	}

	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		log.warn("Inside the  createUser method");
		UserDto user = userService.createUser(userDto);

		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}

	@PutMapping("/{userId}")

	public ResponseEntity<UserDto> updateuser(@Valid @RequestBody UserDto userDto,
			@PathVariable("userId") Integer userId) {

		UserDto user = userService.updateUser(userDto, userId);

		return ResponseEntity.ok().body(user);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable("userId") Integer userId) {

		userService.deleteUser(userId);

		return ResponseEntity.ok().build();

	}

	@DeleteMapping("/")
	public ResponseEntity<ApiResponse> deleteAllUsers() {
		try {
			userService.deleteAllUsers();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED)
					.body(new ApiResponse("Failed to complete the transaction", false));
		}
		return ResponseEntity.ok().body(new ApiResponse("Transaction Completed", true));
	}

}
