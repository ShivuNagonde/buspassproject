package com.spring.service;

import java.util.List;

import com.spring.model.User;

public interface UserService {

	public Object userRegister(User user);

	public List<User> getAllUsers();

	public Object getUserByUserRegId(int id);

	public Object updateUser(User user);

	public Object deleteUserByUserRegId(int id);

	public void deleteAllUsers();

}
