package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

	boolean existsByVehicleStatus(String vehicleStatus);

	boolean existsByVehicleName(String vehicleName);

	Vehicle findByVehicleStatus(String vehicleStatus);

}
