package com.inn.emp.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class EmployeeDto {

	//copy class of main Employee class
	
	 private Integer id;

	 private String fname;
	 
	 private String lname;
	 
	 private String dateOfBirth;
	 
	 private String gender;
	 
	 private String contact;
	 
	 private String email;
	 
	 private String password;
	 
	 private String address;
	 
	 private String role;
	 
	 private String jobDescription ;
	 
	 private String joining;
	 
	 private String img ;
}
