package com.hitech.blogspot.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {
	private Integer UserId;
	@NotEmpty(message = "Username must not be empty")
	@Size(min = 4, message = "The name should minimumbe of 4 characters")
	private String UserFirstName;
	@NotEmpty(message = "Username must not be empty")
	@Size(min = 4, message = "The name should minimumbe of 4 characters")
	private String UserLastName;
	@Email(message = "Please enter a valid email id.")
	@NotEmpty(message = "Email must not be empty")
	private String UserEmailId;
	@NotEmpty(message = "Password must not be empty")
	@Size(min = 4, max = 12, message = "The Password should minimumbe of 4 characters and maximum of 12 characters")
	private String UserPassword;
	@NotEmpty(message = "About must not be empty")
	@Size(min = 4, message = "The name should minimumbe of 4 characters")
	private String UserAbout;

}
