package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.model.PassMaster;

public interface PassMasterRepository extends JpaRepository<PassMaster, Integer> {

	PassMaster findByPassType(String passType);

}
