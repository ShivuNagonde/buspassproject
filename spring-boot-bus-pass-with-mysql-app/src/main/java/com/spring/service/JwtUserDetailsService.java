package com.spring.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.model.User;
import com.spring.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		User u = userRepository.findByUserEmail(userEmail);
		if (u == null) {
			throw new UsernameNotFoundException("User not found with userEmailId: " + userEmail);
			
		}
		return new org.springframework.security.core.userdetails.User(u.getUserEmail(), u.getUserPassword(),
				new ArrayList<>());
	}
	
}