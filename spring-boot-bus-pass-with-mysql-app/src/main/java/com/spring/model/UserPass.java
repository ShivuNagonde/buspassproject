package com.spring.model;

import java.time.LocalDateTime;
import java.util.Date;

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
@Table(name = "bp_user_pass")
public class UserPass {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_pass_id")
	private int id;
	@Column(name = "user_id")
	private int userId;
	@Column(name = "user_role")
	private String userRole;
	@Column(name = "vehicle_status")
	private String vehicleStatus;
	@Column(name = "pass_type")
	private String passType;
	@Column(name = "pass_status")
	private String passStatus;
	@Column(name = "created_date")
	private Date createdDate;
	@Column(name = "expiry_date")
	private LocalDateTime expiryDate;

}
