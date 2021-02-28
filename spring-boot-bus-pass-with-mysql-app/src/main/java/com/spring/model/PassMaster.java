package com.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bp_pass_master")
public class PassMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pass_code")
	private int passCode;
	@Column(name = "pass_status")
	private String passStatus;
	@Column(name = "pass_type")
	private String passType;
	@Column(name = "pass_name")
	private String passName;

}
