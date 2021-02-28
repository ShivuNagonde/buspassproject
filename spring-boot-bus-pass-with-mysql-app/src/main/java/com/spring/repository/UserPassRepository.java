package com.spring.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.model.UserPass;

public interface UserPassRepository extends JpaRepository<UserPass, Integer> {

	List<UserPass> findByUserId(int userRegId);

	// void deleteByUserId(int userRegId);

	List<UserPass> findByExpiryDate(LocalDateTime endOfDay);

	void deleteByExpiryDate(LocalDateTime endOfDay);

	List<UserPass> findByPassStatus(String string);

	// UserPass findByExpiryDate(LocalDateTime endOfDay);

}
