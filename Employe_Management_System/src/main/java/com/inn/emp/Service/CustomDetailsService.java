package com.inn.emp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.inn.emp.entities.Employee;
import com.inn.emp.repository.EmployeeRepository;

@Service
public class CustomDetailsService implements UserDetailsService{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee emp = employeeRepository.findByEmail(username)
		   .orElseThrow(()->new RuntimeException("Username doesn't exist !!!"));
		return emp;
	}

}
