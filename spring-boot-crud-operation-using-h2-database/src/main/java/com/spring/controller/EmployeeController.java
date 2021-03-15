package com.spring.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spring.model.Employee;
import com.spring.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping(value = "/saveEmployee")
	public Employee saveEmployee(@RequestParam("file") MultipartFile file, Employee employee) throws IOException {
		String imageFile = encodeFileToBase64Binary(file.getBytes());
		employee.setImage(imageFile);
		return employeeService.saveEmployee(employee);
	}

	private String encodeFileToBase64Binary(byte[] bytes) {
		String encodedFile = "";
		try {
            encodedFile = Base64.getEncoder().encodeToString(bytes);
        }catch (Exception e) {
            e.printStackTrace();
        }
		return encodedFile;
	}

	@GetMapping(value = "/getAllEmployees")
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	@GetMapping(value = "/getEmployee/{id}")
	public Optional<Employee> getEmployeeById(@PathVariable int id) {
		return employeeService.getEmployeeById(id);
	}
    
	@PutMapping(value = "/updateEmployee")
	public Employee updateEmployee(@RequestBody Employee employee) {
		return employeeService.updateEmployee(employee);
	}

	@DeleteMapping(value = "/deleteEmployee")
	public void deleteEmployeeById(int id) {
		employeeService.deleteEmployeeById(id);
		
	}

	@DeleteMapping(value = "/deleteAllEmployees")
	public void deleteAllEmployees() {
		employeeService.deleteEmployees();
		
	}

}
