package com.spring.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.model.Vehicle;
import com.spring.repository.VehicleRepository;

@Service
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleRepository vehicleRepo;

	@Override
	public Object saveVehicle(Vehicle vehicle) {
		if (vehicleRepo.existsByVehicleStatus(vehicle.getVehicleStatus())) {
			return "This RoleStatus is Already in use.";
		}
		if (vehicleRepo.existsByVehicleName(vehicle.getVehicleName())) {
			return "This RoleName is Already in use.";
		}
		return vehicleRepo.save(vehicle);

	}

	@Override
	public List<Vehicle> getAllVehicle() {
		return vehicleRepo.findAll();
	}

	@Override
	public Object getOneVehicle(int id) {
		Optional<Vehicle> r = vehicleRepo.findById(id);
		if (r.isPresent()) {
			return vehicleRepo.findById(id);
		} else {
			return "Vehicle Code " + id + " Is NOT FOUND.";
		}
	}

	@Override
	public Object updateVehicle(Vehicle vehicle) {
		Map<String, Object> map = new HashMap<>();
		Optional<Vehicle> v = vehicleRepo.findById(vehicle.getVehicleCode());
		if (v.isPresent()) {
			Vehicle vl = v.get();
			vl.setVehicleName(vehicle.getVehicleName());
			vl.setVehicleStatus(vehicle.getVehicleStatus());
			vehicleRepo.save(vl);
			map.put("Status ", "Vehicle id " + vehicle.getVehicleCode() + " is updated successfully.");
		} else {
			map.put("Status ", "Vehicle Id " + vehicle.getVehicleCode() + " Is NOT FOUND.");
		}
		return map;
	}

	@Override
	public Object deleteVehicle(int id) {
		Map<String, Object> map = new HashMap<>();
		Optional<Vehicle> v = vehicleRepo.findById(id);
		if (v.isPresent()) {
			vehicleRepo.deleteById(id);
			map.put("Status ", "Vehicle id " + id + " is deleted successfully.");
		} else {
			map.put("Status ", "Vehicle id " + id + " is NOT FOUND.");
		}
		return map;
	}

	@Override
	public void deleteAllVehicle() {
		vehicleRepo.deleteAll();
	}

}
