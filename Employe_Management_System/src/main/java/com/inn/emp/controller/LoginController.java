package com.inn.emp.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inn.emp.dto.LoginDto;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private final AuthenticationManager authenticationManager;
	
	public LoginController(AuthenticationManager authenticationManager) {
		 this.authenticationManager = authenticationManager;
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/emp/login")
	public ResponseEntity<?>login(@RequestBody(required = true) LoginDto dto){
		try {
			Authentication authenticate = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
			
			SecurityContextHolder.getContext().setAuthentication(authenticate);
			
			
			if(authenticate.isAuthenticated()) {
				return new ResponseEntity<>(Collections.singletonMap("message", "Login Successful"), HttpStatus.OK);
			}else {
		
				return new ResponseEntity<>(Collections.singletonMap("message","Login failed , please check your email and password"),HttpStatus.BAD_REQUEST);
			}
		}catch(AuthenticationException ex){
			 return new ResponseEntity<>("Authentication failed: " + ex.getMessage(), HttpStatus.UNAUTHORIZED);
	    }
		
		
	}

}
