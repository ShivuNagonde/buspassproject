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
@Table(name = "bp_expired_pass")
public class ExpiredPass {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "expired_pass_id")
	private int id;
	@Column(name = "user_id")
	private int userId;
	@Column(name = "user_role")
	private String userRole;
	@Column(name = "vehicle_status")
	private String vehicleStatus;
	@Column(name = "pass_type")
	private String passType;
	@Column(name = "created_date")
	private Date createdDate;
	@Column(name = "expired_date")
	private LocalDateTime expiredDate;
	@Column(name = "row_created_date")
	private Date rowCreatedDate;
}
