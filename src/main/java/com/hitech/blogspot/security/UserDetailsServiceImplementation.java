package com.hitech.blogspot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hitech.blogspot.customexceptions.ResourceNotFoundException;
import com.hitech.blogspot.model.User;
import com.hitech.blogspot.repository.UserRepository;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.findByUserEmailId(username)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Username", username));

		return user;
	}

}
