package com.spring.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.model.User;
import com.spring.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public Object userRegister(User user) {
		if (userRepository.existsByUserRegId(user.getUserRegId())) {
			return "UserRegId " + user.getUserRegId() + " already exists.";
		}
		user.setUserPassword(bcryptEncoder.encode(user.getUserPassword()));
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public Object getUserByUserRegId(int id) {
		Optional<User> u = userRepository.findById(id);
		Map<String, Object> map = new HashMap<>();
		if (!u.isPresent()) {
			map.put("try again!", "User " + id + " is not present.");
		} else {
			map.put("Success", userRepository.findById(id));
		}
		return map;
	}

	@Override
	public Object updateUser(User user) {
		Optional<User> u = userRepository.findById(user.getUserRegId());
		Map<String, Object> map = new HashMap<>();
		if (u.isPresent()) {
			User ur = u.get();
			ur.setUserName(user.getUserName());
			ur.setUserEmail(user.getUserEmail());
			ur.setUserPassword(user.getUserPassword());
			ur.setUserAddress(user.getUserAddress());
			ur.setUserLivingAddress(user.getUserLivingAddress());
			ur.setUserPhoneNo(user.getUserPhoneNo());
			ur.setUserRoleStatus(user.getUserRoleStatus());
			userRepository.save(ur);
			map.put("Status ", "User " + ur.getUserRegId() + " updated successfully.");
			// userRepository.save(ur);
			// return "user updated successfully.";
		} else {
			map.put("try again! ", "User " + user.getUserRegId() + " is Not Found.");
		}
		return map;
	}

	@Override
	public Object deleteUserByUserRegId(int id) {
		Map<String, Object> map = new HashMap<>();
		Optional<User> u = userRepository.findById(id);
		if (u.isPresent()) {
			userRepository.deleteById(id);
			map.put("Status ", "UserId " + id + " has been deleted successfully.");
		} else {
			map.put("Status ", "User " + id + " is Not Found.");
		}
		return map;
	}

	@Override
	public void deleteAllUsers() {
		userRepository.deleteAll();
	}
}
