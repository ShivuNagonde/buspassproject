package com.spring.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.been.UserPassDetails;
import com.spring.model.ExpiredPass;
import com.spring.model.PassMaster;
import com.spring.model.Role;
import com.spring.model.User;
import com.spring.model.UserPass;
import com.spring.repository.ExpiredPassRepository;
import com.spring.repository.PassMasterRepository;
import com.spring.repository.RoleRepository;
import com.spring.repository.UserPassRepository;
import com.spring.repository.UserRepository;
import com.spring.service.PassMasterService;

@RestController
@RequestMapping("/create")
@Transactional
public class PassController {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	private PassMasterRepository masterRepo;
	@Autowired
	private PassMasterService passMasterService;
	@Autowired
	private UserPassRepository userPassRepo;
	@Autowired
	private ExpiredPassRepository expiredPassRepository;

	String expired = "Expired";
	String active = "Running";

	// localhost:8080/create/createDayPass/{userRegId}/{vehicleStatus}
	@RequestMapping(value = "/createDayPass/{userRegId}/{vehicleStatus}", method = RequestMethod.POST, produces = "application/json")
	public Object createDayPass(@PathVariable("userRegId") int userRegId,
			@PathVariable("vehicleStatus") String vehicleStatus) {

		UserPass pass = new UserPass();
		Optional<User> u = userRepo.findByUserRegId(userRegId);
		User u1 = u.get();
		Role r = roleRepo.findByRoleStatus(u1.getUserRoleStatus());
		String day = "Day";
		PassMaster pm = masterRepo.findByPassType(day);

		LocalDate date = LocalDate.now();
		LocalDateTime endOfDay = LocalDateTime.of(date, LocalTime.of(11, 59, 59));
		if (u.isPresent()) {
			pass.setUserId(userRegId);
			pass.setUserRole(r.getRoleName());
			pass.setPassType(pm.getPassName());
			pass.setPassStatus(pm.getPassStatus());
			pass.setVehicleStatus(vehicleStatus);
			pass.setCreatedDate(new Date());
			pass.setExpiryDate(endOfDay);

			return userPassRepo.save(pass);
		} else {
			return "User " + userRegId + " Is Not Registerd,Please Register Your Account.";
		}

	}

	// localhost:8080/create/createMonthPass/{userRegId}/{vehicleStatus}
	@RequestMapping(value = "/createMonthPass/{userRegId}/{vehicleStatus}", method = RequestMethod.POST, produces = "application/json")
	public Object createMonthPass(@PathVariable("userRegId") int userRegId,
			@PathVariable("vehicleStatus") String vehicleStatus) {

		UserPass pass = new UserPass();
		Optional<User> u = userRepo.findById(userRegId);
		User u1 = u.get();
		Role r = roleRepo.findByRoleStatus(u1.getUserRoleStatus());
		String month = "Month";
		PassMaster pm = masterRepo.findByPassType(month);

		LocalDate date = LocalDate.of(2021, Month.FEBRUARY, 28);
		LocalDateTime endOfMonth = LocalDateTime.of(date, LocalTime.of(11, 59, 59));
		if (u.isPresent()) {
			pass.setUserId(userRegId);
			pass.setUserRole(r.getRoleName());
			pass.setPassType(pm.getPassName());
			pass.setPassStatus(pm.getPassStatus());
			pass.setVehicleStatus(vehicleStatus);
			pass.setCreatedDate(new Date());
			pass.setExpiryDate(endOfMonth);
			return userPassRepo.save(pass);
		} else {
			return "User " + userRegId + " Is Not Registerd,Please Register Your Account.";
		}
	}

	// localhost:8080/create/createYearPass/{userRegId}/{vehicleStatus}
	@RequestMapping(value = "/createYearPass/{userRegId}/{vehicleStatus}", method = RequestMethod.POST, produces = "application/json")
	public Object createYearPass(@PathVariable("userRegId") int userRegId,
			@PathVariable("vehicleStatus") String vehicleStatus) {

		UserPass pass = new UserPass();
		Optional<User> u = userRepo.findById(userRegId);
		User u1 = u.get();
		Role r = roleRepo.findByRoleStatus(u1.getUserRoleStatus());
		String year = "Year";
		PassMaster pm = masterRepo.findByPassType(year);

		LocalDate date = LocalDate.of(2022, Month.MARCH, 31);
		LocalDateTime endOfDay = LocalDateTime.of(date, LocalTime.of(11, 59, 59));
		if (u.isPresent()) {
			pass.setUserId(userRegId);
			pass.setUserRole(r.getRoleName());
			pass.setPassType(pm.getPassName());
			pass.setPassStatus(pm.getPassStatus());
			pass.setVehicleStatus(vehicleStatus);
			pass.setCreatedDate(new Date());
			pass.setExpiryDate(endOfDay);
			return userPassRepo.save(pass);
		} else {
			return "User " + userRegId + " Is Not Registerd,Please Register Your Account.";
		}
	}

	// it gets only all expired data from the Expired pass table
	// localhost:8080/create/getPassDetailsByUserRegId/{userRegId}
	@RequestMapping(value = "/getPassDetailsByUserRegId/{userRegId}", method = RequestMethod.GET, produces = "application/json")
	public List<UserPassDetails> getPassDetailsByUserRegId(@PathVariable("userRegId") int userRegId) {
		return passMasterService.findUserPassDetailsByUserRegId(userRegId);
	}

	// it deletes the data from expired pass table by userRegId
	// localhost:8080/create/deleteUserPassDetailsByUserId/{userRegId}
	@RequestMapping(value = "/deleteUserPassDetailsByUserId/{userRegId}", method = RequestMethod.DELETE, produces = "application/json")
	public Object deleteUserPassDetailsByUserId(@PathVariable("userRegId") int userRegId) {
		return passMasterService.deleteUserPassDetailsByUserId(userRegId);
	}

	// replace running to expired in different table
	@Scheduled(cron = "1 37 17 * * ?")
	public List<UserPass> expireDayPass() {
		List<UserPass> al = new ArrayList<>();
		ExpiredPass ep = new ExpiredPass();

		LocalDate date = LocalDate.now();
		LocalDateTime endOfDay = LocalDateTime.of(date, LocalTime.of(11, 59, 59));
		List<UserPass> up = userPassRepo.findByExpiryDate(endOfDay);
		al.addAll(up);
		if (al != null) {
			for (UserPass u : al) {
				List<ExpiredPass> el = new ArrayList<>();
				ep.setId(u.getId());
				ep.setUserId(u.getUserId());
				ep.setUserRole(u.getUserRole());
				ep.setPassType(u.getPassType());
				ep.setVehicleStatus(u.getVehicleStatus());
				ep.setCreatedDate(u.getCreatedDate());
				ep.setExpiredDate(u.getExpiryDate());
				ep.setRowCreatedDate(new Date());
				el.add(ep);
				expiredPassRepository.saveAll(el);
			}
		}
		userPassRepo.deleteByExpiryDate(endOfDay);
		return al;

	}

	// replace running to expired in same table
	@Scheduled(cron = "0 46 19 * * ?")
	public List<UserPass> expireDaysPass() {
		List<UserPass> list = new ArrayList<>();
		LocalDate date = LocalDate.now();
		LocalDateTime endOfDay = LocalDateTime.of(date, LocalTime.of(11, 59, 59));
		List<UserPass> u = userPassRepo.findByExpiryDate(endOfDay);
		list.addAll(u);
		if (list != null) {
			for (UserPass u1 : list) {
				u1.setPassStatus(expired);
				userPassRepo.save(u1);
			}
		}
		return null;
	}

	// get user pass when it is not expired in 1st case(using different table)
	// localhost:8080/create/getUserPassById/{userRegId}
	@RequestMapping(value = "/getUserPassById/{userRegId}", method = RequestMethod.GET, produces = "application/json")
	public Object getUserPassById(@PathVariable("userRegId") int userId) {
		List<UserPass> p = userPassRepo.findByUserId(userId);
		if (!p.isEmpty()) {
			return p;
		} else {
			return "Not Found.";
		}
	}

	// get userpass when it is not expired in 2nd case(using same table)
	// localhost:8080/create/getAllActiveUserPass
	@RequestMapping(value = "/getAllActiveUserPass", method = RequestMethod.GET, produces = "application/json")
	public Object getAllActiveUserPass() {
		List<UserPass> p = userPassRepo.findByPassStatus(active);
		if (p != null) {
			return p;
		} else {
			return "Not Found.";
		}
	}

	// get userpass when it is expired in 2nd case(using same table)
	// localhost:8080/create/getAllExpiredUserPass
	@RequestMapping(value = "/getAllExpiredUserPass", method = RequestMethod.GET, produces = "application/json")
	public Object getAllExpiredUserPass() {
		List<UserPass> p = userPassRepo.findByPassStatus(expired);
		if (p != null) {
			return p;
		} else {
			return "Not Found.";
		}
	}
}
//**Case1: i am using two different tables 1 for saving running passes "userpass", 
//2nd  for saving expired passes, when passes are expired, the hole data of passes is deleted in userpass table.
//**Case2: i am using only 1 table for saving both type of passes,and 2 apis are used for accessing 1 for active passes and 2nd for expired passes 