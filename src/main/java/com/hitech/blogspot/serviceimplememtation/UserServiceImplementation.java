package com.hitech.blogspot.serviceimplememtation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hitech.blogspot.customexceptions.ResourceNotFoundException;
import com.hitech.blogspot.dto.UserDto;
import com.hitech.blogspot.model.User;
import com.hitech.blogspot.repository.UserRepository;
import com.hitech.blogspot.service.UserService;

@Service
public class UserServiceImplementation implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto getUserbyId(Integer userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		UserDto userToUserDto = userToUserDto(user);

		return userToUserDto;
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> userList = userRepository.findAll();
		List<UserDto> userDtoList = userList.stream().map(user -> userToUserDto(user)).collect(Collectors.toList());

		return userDtoList;
	}

	@Override
	public UserDto updateUser(UserDto userdto, Integer userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		user.setUserFirstName(userdto.getUserFirstName());
		user.setUserLastName(userdto.getUserLastName());
		user.setUserEmailId(userdto.getUserEmailId());
		user.setUserAbout(userdto.getUserAbout());
		user.setUserPassword(userdto.getUserPassword());
		user = userRepository.save(user);
		return userToUserDto(user);

	}

	@Override
	public void deleteUser(Integer userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		userRepository.delete(user);

	}

	@Override
	public UserDto createUser(UserDto userDto) {

		User user = userDtoToUser(userDto);
		user = userRepository.save(user);
		return userToUserDto(user);

	}

	@Override
	public void deleteAllUsers() {
		userRepository.deleteAll();

	}

	public User userDtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);

		return user;

	}

	public UserDto userToUserDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;

	}

}
