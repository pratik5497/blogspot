package com.hitech.blogspot.service;

import java.util.List;

import com.hitech.blogspot.dto.UserDto;

public interface UserService {
	public abstract UserDto getUserbyId(Integer userId);

	public abstract List<UserDto> getAllUsers();

	public abstract UserDto updateUser(UserDto userdto, Integer userId);

	public abstract void deleteUser(Integer userId);

	public abstract UserDto createUser(UserDto userDto, Integer roleId);

	public abstract void deleteAllUsers();

}
