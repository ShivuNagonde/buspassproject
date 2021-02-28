package com.spring.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.model.Role;
import com.spring.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepo;

	@Override
	public Object saveRole(Role role) {
		if (roleRepo.existsByRoleStatus(role.getRoleStatus())) {
			return "This RoleStatus is Already in use.";
		}
		if (roleRepo.existsByRoleName(role.getRoleName())) {
			return "This RoleName is Already in use.";
		}
		return roleRepo.save(role);
	}

	@Override
	public List<Role> getAllRoles() {
		return roleRepo.findAll();
	}

	@Override
	public Object getOneRole(int id) {
		Optional<Role> r = roleRepo.findById(id);
		if (r.isPresent()) {
			return roleRepo.findById(id);
		} else {
			return "Role Id " + id + " Is NOT FOUND.";
		}
	}

	@Override
	public Object updateRole(Role role) {
		Map<String, Object> map = new HashMap<>();
		Optional<Role> r = roleRepo.findById(role.getRoleId());
		if (r.isPresent()) {
			Role rl = r.get();
			rl.setRoleName(role.getRoleName());
			rl.setRoleStatus(role.getRoleStatus());
			map.put("Role Id " + rl.getRoleId() + "is updated successfully.", roleRepo.save(rl));
			// return roleRepo.save(rl);
		} else {
			map.put("Status ", "Role Id " + role.getRoleId() + " Is NOT FOUND.");
		}
		return map;
	}

	@Override
	public Object deleteRole(int id) {
		Map<String, Object> map = new HashMap<>();
		Optional<Role> r = roleRepo.findById(id);
		if (r.isPresent()) {
			roleRepo.deleteById(id);
			map.put("Status ", "Role id " + id + " is successfully deleted.");
		} else {
			map.put("Status ", "Role id " + id + " is NOT FOUND");
		}
		return map;

	}

	@Override
	public void deleteAllRole() {
		roleRepo.deleteAll();

	}

}
