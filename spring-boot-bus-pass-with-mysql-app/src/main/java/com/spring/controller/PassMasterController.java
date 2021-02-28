package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.model.PassMaster;
import com.spring.service.PassMasterService;

@RestController
@RequestMapping("/pass")
public class PassMasterController {

	@Autowired
	private PassMasterService masterService;

	// localhost:8080/pass/savePassMaster
	@RequestMapping(value = "/savePassMaster", method = RequestMethod.POST, produces = "application/json")
	public Object savePassMaster(@RequestBody PassMaster master) {
		return masterService.savePassMaster(master);
	}

	// localhost:8080/pass/getAllPassMasters
	@RequestMapping(value = "/getAllPassMasters", method = RequestMethod.GET, produces = "application/json")
	public List<PassMaster> getAllPassMasters() {
		return masterService.getAllPassMasters();
	}

	// localhost:8080/pass/getPassMasterById/{passCode}
	@RequestMapping(value = "/getPassMasterById/{passCode}", method = RequestMethod.GET, produces = "application/json")
	public Object getPassMasterById(@PathVariable("passCode") int passCode) {
		return masterService.getPassMasterById(passCode);
	}

	// localhost:8080/pass/deletePassMasterById/{passCode}
	@RequestMapping(value = "/deletePassMasterById/{passCode}", method = RequestMethod.DELETE, produces = "application/json")
	public Object deletePassMasterById(@PathVariable("passCode") int passCode) {
		return masterService.deletePassMasterById(passCode);
	}

}
