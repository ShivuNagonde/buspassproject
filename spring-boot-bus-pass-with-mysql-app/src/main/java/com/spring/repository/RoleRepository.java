package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByRoleStatus(String userRoleStatus);

	boolean existsByRoleStatus(String roleStatus);

	boolean existsByRoleName(String roleName);

}
