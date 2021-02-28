package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.model.Role;
import com.spring.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	// localhost:8080/role/saveRole
	@RequestMapping(value = "/saveRole", method = RequestMethod.POST, produces = "application/json")
	public Object saveRole(@RequestBody Role role) {
		return roleService.saveRole(role);
	}

	// localhost:8080/role/getAllRole
	@RequestMapping(value = "/getAllRole", method = RequestMethod.GET, produces = "application/json")
	public List<Role> getAllRoles() {
		return roleService.getAllRoles();
	}

	// localhost:8080/role/getOneRole/{id}
	@RequestMapping(value = "/getOneRole/{id}", method = RequestMethod.GET, produces = "application/json")
	public Object getOneRole(@PathVariable("id") int id) {
		return roleService.getOneRole(id);
	}

	// localhost:8080/role/updateRole
	@RequestMapping(value = "/updateRole", method = RequestMethod.PUT, produces = "application/json")
	public Object updateRole(@RequestBody Role role) {
		return roleService.updateRole(role);
	}

	// localhost:8080/role/deleteRole/{id}
	@RequestMapping(value = "/deleteRole/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public Object deleteRole(@PathVariable("id") int id) {
		return roleService.deleteRole(id);

	}

	// localhost:8080/role/deleteAllRole
	@RequestMapping(value = "/deleteAllRole", method = RequestMethod.DELETE, produces = "application/json")
	public String deleteAllRole() {
		roleService.deleteAllRole();
		return "All Roles are deleted successfully.";

	}

}
