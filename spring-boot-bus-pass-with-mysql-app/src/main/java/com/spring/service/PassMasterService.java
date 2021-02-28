package com.spring.service;

import java.util.List;

import com.spring.been.UserPassDetails;
import com.spring.model.PassMaster;

public interface PassMasterService {

	public Object savePassMaster(PassMaster master);

	public Object updatePassMaster(PassMaster master);

	public List<PassMaster> getAllPassMasters();

	public Object getPassMasterById(int passCode);

	public Object deletePassMasterById(int passCode);

	public Object deleteUserPassDetailsByUserId(int userRegId);

	public List<UserPassDetails> findUserPassDetailsByUserRegId(int userRegId);
}
