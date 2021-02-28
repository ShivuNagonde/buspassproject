package com.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUserEmail(String userEmail);

	Optional<User> findByUserRegId(int userRegId);

	boolean existsByUserRegId(int userRegId);

}
