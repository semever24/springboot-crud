/**
 * @Since 
 * @author Senthil Kumar R
 */
package com.example.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.entity.Employee;
import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Employee saveEmployees(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	
	public Employee getEmployeeById(long id) throws ResourceNotFoundException {
		return employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee", "Id", id));
	}
	
	public Employee updateEmployee(Employee employee, long id) throws ResourceNotFoundException {
		Employee existingEmployee= employeeRepository.findById(id).orElseThrow(
																  () -> new ResourceNotFoundException("Employee", "Id", id));
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmailId(employee.getEmailId());
		existingEmployee.setDob(employee.getDob());
		existingEmployee.setLocation(employee.getLocation());
		employeeRepository.save(existingEmployee);
		
		return existingEmployee;
	}
	
	public void deleteEmployee(long id) throws ResourceNotFoundException {
		employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
		employeeRepository.deleteById(id);
	}
}
