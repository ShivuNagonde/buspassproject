package com.spring.service;

import org.springframework.stereotype.Component;

import com.spring.been.UserPassDetails;
import com.spring.model.ExpiredPass;

@Component
public class UserPassServiceHelper {

	public UserPassDetails converToUserPassDomain(ExpiredPass userPass) {
		return UserPassDetails.builder().userId(userPass.getUserId()).userRole(userPass.getUserRole())
				.passType(userPass.getPassType()).vehicleStatus(userPass.getVehicleStatus())
				.createdDate(userPass.getCreatedDate()).expiryDate(userPass.getExpiredDate()).build();

	}
}
