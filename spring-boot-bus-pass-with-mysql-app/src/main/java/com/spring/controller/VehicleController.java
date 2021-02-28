package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.model.Vehicle;
import com.spring.service.VehicleService;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;

	// localhost:8080/vehicle/saveVehicle
	@RequestMapping(value = "/saveVehicle", method = RequestMethod.POST, produces = "application/json")
	public Object saveVehicle(@RequestBody Vehicle vehicle) {
		return vehicleService.saveVehicle(vehicle);

	}

	// localhost:8080/vehicle/getAllVehicle
	@RequestMapping(value = "/getAllVehicle", method = RequestMethod.GET, produces = "application/json")
	public List<Vehicle> getAllVehicle() {
		return vehicleService.getAllVehicle();
	}

	// localhost:8080/vehicle/getOneVehicle/{vehicleCode}
	@RequestMapping(value = "/getOneVehicle/{vehicleCode}", method = RequestMethod.GET, produces = "application/json")
	public Object getOneVehicle(@PathVariable("vehicleCode") int id) {
		return vehicleService.getOneVehicle(id);
	}

	// localhost:8080/vehicle/updateVehicle
	@RequestMapping(value = "/updateVehicle", method = RequestMethod.PUT, produces = "application/json")
	public Object updateVehicle(@RequestBody Vehicle vehicle) {
		return vehicleService.updateVehicle(vehicle);
	}

	// localhost:8080/vehicle/deleteVehicle/{vehicleCode}
	@RequestMapping(value = "/deleteVehicle/{vehicleCode}", method = RequestMethod.DELETE, produces = "application/json")
	public Object deleteVehicle(@PathVariable("vehicleCode") int id) {
		return vehicleService.deleteVehicle(id);

	}

	// localhost:8080/vehicle/deleteAllVehicle
	@RequestMapping(value = "/deleteAllVehicle", method = RequestMethod.DELETE, produces = "application/json")
	public String deleteAllVehicle() {
		vehicleService.deleteAllVehicle();
		return "All Vehicles are deleted successfully.";
	}

}
