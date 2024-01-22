package com.inn.emp.Service;

import java.util.List;

import com.inn.emp.dto.EmployeeDto;

public interface EmployeeService {

	

	//create Employee
	EmployeeDto createEmp(EmployeeDto employeeDto );
	
	//update Employee
	EmployeeDto updateEmp(EmployeeDto employeeDto, Integer id);
	
	//delete User 
	void deleteEmployee(Integer id);
	
	
	//to get List of all Employee
	List<EmployeeDto> getAllEmp();
	
	//to get Employee's details  by id
	EmployeeDto getById(Integer id);
	
	//to get Employee's details by email
	EmployeeDto getByEmail(String email);
	
	//to get list of Employees by job description
	List<EmployeeDto> getByJobDescription(String jobDescription);
	
	//to get list of Employees by address
	List<EmployeeDto> getByAddress(String address);
	
	//to get list of Employees by joining year
	List<EmployeeDto> getByJoining(String joining);
	
	//to get list of Employees by specific Role
	List<EmployeeDto> getByRole(String joining);
}
