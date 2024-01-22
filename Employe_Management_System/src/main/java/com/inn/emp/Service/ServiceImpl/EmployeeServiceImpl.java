package com.inn.emp.Service.ServiceImpl;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.inn.emp.Service.EmployeeService;
import com.inn.emp.dto.EmployeeDto;
import com.inn.emp.entities.Employee;
import com.inn.emp.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public EmployeeDto createEmp(EmployeeDto employeeDto) {
		//DTO to Entity Conversion
		Employee e = dtoToEntity(employeeDto);
		//save the data
		Employee savedEmp = employeeRepository.save(e);
		//Entity to dto
		EmployeeDto savedDto = entityToDto(e);
		return savedDto;
	}

	@Override
	public EmployeeDto updateEmp(EmployeeDto employeeDto, Integer id) {
		
		Employee e = employeeRepository.findById(id).orElseThrow(()-> new RuntimeException("User Not Found"));
		e.setFname(employeeDto.getFname());
		e.setLname(employeeDto.getLname());
		e.setDateOfBirth(employeeDto.getDateOfBirth());
		e.setGender(employeeDto.getGender());
		e.setContact(employeeDto.getContact());
		e.setEmail(employeeDto.getEmail());
		e.setPassword(employeeDto.getPassword());
		e.setAddress(employeeDto.getAddress());
		e.setJoining(employeeDto.getJoining());
		e.setRole(employeeDto.getRole());
		e.setJobDescription(employeeDto.getJobDescription());
		e.setImg(employeeDto.getImg());
		
		Employee saveEmp = employeeRepository.save(e);
		EmployeeDto savedDto = entityToDto(saveEmp);
		return savedDto;
	}

	@Override
	public void deleteEmployee(Integer id) {
		Employee e = employeeRepository.findById(id).orElseThrow(()-> new RuntimeException("User Not Found"));
		employeeRepository.delete(e);
	}

	@Override
	public List<EmployeeDto> getAllEmp() {
		List<Employee> empList = employeeRepository.findAll();
		List<EmployeeDto> dtolist = empList.stream().map(user->entityToDto(user)).collect(Collectors.toList());
		
		return dtolist;
	}

	@Override
	public EmployeeDto getById(Integer id) {
		Employee empList = employeeRepository.findById(id).orElseThrow(()->new RuntimeException("User Not found"));
		EmployeeDto dto = entityToDto(empList);
		
		return dto;
	}

	@Override
	public EmployeeDto getByEmail(String email) {
		Employee emp = employeeRepository.findByEmail(email).orElseThrow(()->new RuntimeException());
		EmployeeDto dto = entityToDto(emp);
		
		return dto;
	}

	@Override
	public List<EmployeeDto> getByJobDescription(String jobDescription) {
		List<Employee> empList = employeeRepository.findByJobDescription(jobDescription).orElseThrow(()->new RuntimeException("Job description looking for doesn't exist"));
		List<EmployeeDto> dtolist = empList.stream().map(emp->entityToDto(emp)).collect(Collectors.toList());
		
		return dtolist;
	}


	@Override
	public List<EmployeeDto> getByAddress(String addres) {
		List<Employee> empList = employeeRepository.findByAddress(addres).orElseThrow(()->new RuntimeException("Address Not found"));
		List<EmployeeDto> dtolist = empList.stream().map(emp->entityToDto(emp)).collect(Collectors.toList());
		
		return dtolist;
	}

	@Override
	public List<EmployeeDto> getByJoining(String joining) {
		List<Employee> empList = employeeRepository.findByJoining(joining).orElseThrow(()->new RuntimeException("Bad Request year doen't exist!"));
		List<EmployeeDto> dtolist = empList.stream().map(emp->entityToDto(emp)).collect(Collectors.toList());
		
		return dtolist;
	}
  
	// DTO(Data transfer object)  to entity
	public Employee dtoToEntity(EmployeeDto employeeDto) {
		Employee emp = Employee.builder()
		.id(employeeDto.getId())
		.fname(employeeDto.getFname())
		.lname(employeeDto.getLname())
		.dateOfBirth(employeeDto.getDateOfBirth())
		.gender(employeeDto.getGender())
		.contact(employeeDto.getContact())
		.email(employeeDto.getEmail())
		.password(encoder.encode(employeeDto.getPassword()))
		.address(employeeDto.getAddress())
		.joining(employeeDto.getJoining())
		.role(employeeDto.getRole())
		.jobDescription(employeeDto.getJobDescription())
		.img(employeeDto.getImg())
		.build();
		
	   return emp;
	}
	
	// DTO(Data transfer object)  to entity
		public EmployeeDto entityToDto(Employee employee) {
			EmployeeDto dto = EmployeeDto.builder()
			.id(employee.getId())
			.fname(employee.getFname())
			.lname(employee.getLname())
			.dateOfBirth(employee.getDateOfBirth())
			.gender(employee.getGender())
			.contact(employee.getContact())
			.email(employee.getEmail())
			.password(employee.getPassword())
			.address(employee.getAddress())
			.joining(employee.getJoining())
			.role(employee.getRole())
			.jobDescription(employee.getJobDescription())
			.img(employee.getImg())
			.build();
			
		   return dto;
		}

	@Override
	public List<EmployeeDto> getByRole(String role) {
		List<Employee> erole = employeeRepository.findByRole(role).orElseThrow(()->new RuntimeException("role does not exist"));
        List<EmployeeDto> dto = erole.stream().map(r->entityToDto(r)).collect(Collectors.toList());
		
		return dto;
	}
	
}
