package com.inn.emp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inn.emp.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	Optional<Employee> findByEmail(String email);
	
	Optional<List<Employee>> findByJobDescription(String jobDescription);
	
	Optional<List<Employee>> findByAddress(String address);
	
	Optional<List<Employee>> findByJoining(String joining);
	
	Optional<List<Employee>> findByRole(String joining);

}
