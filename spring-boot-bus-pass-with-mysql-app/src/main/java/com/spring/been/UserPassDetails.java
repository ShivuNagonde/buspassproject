package com.spring.been;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserPassDetails {

	// private int id;
	private int userId;
	private String userRole;
	private String vehicleStatus;
	private String passType;
	private Date createdDate;
	private LocalDateTime expiryDate;
}
