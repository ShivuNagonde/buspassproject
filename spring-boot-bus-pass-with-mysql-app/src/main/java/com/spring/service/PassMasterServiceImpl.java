package com.spring.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.been.UserPassDetails;
import com.spring.model.ExpiredPass;
import com.spring.model.PassMaster;
import com.spring.model.User;
import com.spring.repository.ExpiredPassRepository;
import com.spring.repository.PassMasterRepository;
import com.spring.repository.UserRepository;

@Service
@Transactional
public class PassMasterServiceImpl implements PassMasterService {

	@Autowired
	private PassMasterRepository masterRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ExpiredPassRepository expRepo;
	@Autowired
	private UserPassServiceHelper userPassServiceHelper;

	@Override
	public Object savePassMaster(PassMaster master) {
		return masterRepo.save(master);
	}

	@Override
	public List<PassMaster> getAllPassMasters() {
		return masterRepo.findAll();
	}

	@Override
	public Object updatePassMaster(PassMaster master) {
		Map<String, Object> map = new HashMap<>();
		Optional<PassMaster> pm = masterRepo.findById(master.getPassCode());
		if (pm.isPresent()) {
			PassMaster pm1 = pm.get();
			pm1.setPassName(master.getPassName());
			pm1.setPassStatus(master.getPassStatus());
			pm1.setPassType(master.getPassType());
			map.put("PassMaster " + master.getPassCode() + "", masterRepo.save(pm1));
		} else {
			map.put("Status ", "PassMaster " + master.getPassCode() + "is NOT FOUND.");
		}
		return map;
	}

	@Override
	public Object getPassMasterById(int passCode) {
		Optional<PassMaster> pm = masterRepo.findById(passCode);
		if (pm.isPresent()) {
			return masterRepo.findById(passCode);
		} else {
			return "Passcode " + passCode + "is NOT FOUND.";
		}
	}

	@Override
	public Object deletePassMasterById(int passCode) {
		Map<String, Object> map = new HashMap<>();
		Optional<PassMaster> pm = masterRepo.findById(passCode);
		if (pm.isPresent()) {
			masterRepo.deleteById(passCode);
			map.put("Status ", "Passcode " + passCode + " is deleted successfully.");
		} else {
			map.put("Status ", "Passcode " + passCode + " is NOT FOUND.");
		}
		return map;
	}

	@Override
	public List<UserPassDetails> findUserPassDetailsByUserRegId(int userRegId) {
		List<UserPassDetails> details = new ArrayList<>();
		Optional<User> u = userRepo.findByUserRegId(userRegId);
		if (u.isPresent()) {
			Optional<List<ExpiredPass>> up = expRepo.findByUserId(userRegId);
			if (up.isPresent()) {
				up.get().forEach(userPass -> {
					details.add(userPassServiceHelper.converToUserPassDomain(userPass));
				});
			}
		}
		return details;
	}

	@Override
	public Object deleteUserPassDetailsByUserId(int userRegId) {
		Map<String, Object> map = new HashMap<>();
		Optional<List<ExpiredPass>> up = expRepo.findByUserId(userRegId);
		if (up.isPresent()) {
			expRepo.deleteByUserId(userRegId);
			map.put("Status ", "History of UserRegId " + userRegId + " is deleted Successfully.");
		} else {
			map.put("Status ", "UserRegId " + userRegId + "is NOT FOUND.");
		}
		return map;
	}
}
