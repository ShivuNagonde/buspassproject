package com.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.model.ExpiredPass;

public interface ExpiredPassRepository extends JpaRepository<ExpiredPass, Integer> {

	Optional<List<ExpiredPass>> findByUserId(int userRegId);

	void deleteByUserId(int userId);

	// @Query(value = "INSERT INTO userdb1.bp_expired_pass (user_id, user_role,
	// pass_type) SELECT user_id, user_role, pass_type FROM userdb1.bp_user_pass",
	// nativeQuery = true)
	// List<ExpiredPass> saveAll(UserPass u);

}
