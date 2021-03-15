package com.spring.service;

import java.util.List;
import java.util.Optional;

import com.spring.model.Employee;

public interface EmployeeService {

	public Employee saveEmployee(Employee employee);
	public List<Employee> getAllEmployees();
	public Optional<Employee> getEmployeeById(int id);
	public Employee updateEmployee(Employee employee);
	public void deleteEmployeeById(int id);
	public void deleteEmployees();
}
