package com.spring.service;

import java.util.List;

import com.spring.model.Vehicle;

public interface VehicleService {

	public Object saveVehicle(Vehicle vehicle);

	public List<Vehicle> getAllVehicle();

	public Object getOneVehicle(int id);

	public Object updateVehicle(Vehicle vehicle);

	public Object deleteVehicle(int id);

	public void deleteAllVehicle();
}
