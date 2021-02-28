package com.spring.service;

import java.util.List;

import com.spring.model.Role;

public interface RoleService {

	public Object saveRole(Role role);

	public List<Role> getAllRoles();

	public Object getOneRole(int id);

	public Object updateRole(Role role);

	public Object deleteRole(int id);

	public void deleteAllRole();
}
