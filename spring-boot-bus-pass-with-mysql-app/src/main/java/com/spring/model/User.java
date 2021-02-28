package com.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bp_user_register")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_reg_id")
	@NotNull
	private int userRegId;
	@NotNull
	@Column(name = "user_name")
	private String userName;
	@NotNull
	@Column(name = "user_address")
	private String userAddress;
	@NotNull
	@Column(name = "user_adhar_no")
	private String userAdharNo;
	@NotNull
	@Column(name = "user_adhar_card")
	private String userAdharCard;
	@Column(name = "user_photo")
	private String userPhoto;
	@NotNull
	@Column(name = "user_living_address")
	private String userLivingAddress;
	@NotNull
	@Column(name = "user_phone_no")
	private String userPhoneNo;
	@NotNull
	@Column(name = "user_email")
	private String userEmail;
	@NotNull
	@Column(name = "user_password")
	private String userPassword;
	@NotNull
	@Column(name = "user_role_status")
	private String userRoleStatus;
	@Column(name = "user_text1")
	private String userText1;

}
