package com.inn.emp.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.inn.emp.Service.EmployeeService;
import com.inn.emp.Service.FileService;
import com.inn.emp.dto.EmployeeDto;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/emp")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private FileService fileService;
	
    @Value("${user.profile.image.path}")
	private String ImagePath;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/signUp")
	public ResponseEntity<EmployeeDto> addEmp(/*@Valid*/@RequestBody EmployeeDto dto){
		EmployeeDto create = employeeService.createEmp(dto);
		
		return new ResponseEntity<EmployeeDto>(create,HttpStatus.CREATED);
	}

	@GetMapping("/emplist")
	public ResponseEntity<List<EmployeeDto>> getEmp(){
		List<EmployeeDto> all = employeeService.getAllEmp();
		
		return new ResponseEntity<List<EmployeeDto>>(all,HttpStatus.CREATED );
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/updateEmp/{id}")
	public ResponseEntity<EmployeeDto> updateEmp(@PathVariable("id") Integer id ,@RequestBody EmployeeDto edto){
		EmployeeDto dto = employeeService.updateEmp(edto,id);
		return new ResponseEntity<EmployeeDto>(dto,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteUser(@PathVariable("id") Integer id){
		employeeService.deleteEmployee(id);
		
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getid/{id}")
	public ResponseEntity<EmployeeDto> getbyId(@PathVariable("id") Integer id){
		EmployeeDto byId = employeeService.getById(id);
		return new ResponseEntity<EmployeeDto>(byId,HttpStatus.OK);
	}

	@GetMapping("/getemail/{email}")
	public ResponseEntity<EmployeeDto> getbyEmail1(@PathVariable("email") String email){
		EmployeeDto byemail = employeeService.getByEmail(email);
		return new ResponseEntity<EmployeeDto>(byemail,HttpStatus.OK);
	}


	@GetMapping("/jobDesc/{job}")
	public ResponseEntity<List<EmployeeDto>> getbyJob(@PathVariable("job") String job){
		List<EmployeeDto> byjob = employeeService.getByJobDescription(job);
		return new ResponseEntity<List<EmployeeDto>>(byjob,HttpStatus.OK);
	}
	

	@GetMapping("/add/{address}")
	public ResponseEntity<List<EmployeeDto>> getbyadd(@PathVariable("address") String address){
		List<EmployeeDto> add = employeeService.getByAddress(address);
		return new ResponseEntity<List<EmployeeDto>>(add,HttpStatus.OK);
	}
	

	@GetMapping("/year/{joining}")
	public ResponseEntity<List<EmployeeDto>> getbyjoining(@PathVariable("joining") String joining){
		List<EmployeeDto> byjoining = employeeService.getByJoining(joining);
		return new ResponseEntity<List<EmployeeDto>>(byjoining,HttpStatus.OK);
	}
	

	@GetMapping("/r=/{role}")
	public ResponseEntity<List<EmployeeDto>> getbyrole(@PathVariable("role") String role){
		List<EmployeeDto> byrole = employeeService.getByRole(role);
		return new ResponseEntity<List<EmployeeDto>>(byrole,HttpStatus.OK);
	}
	
	@PostMapping("/Image/{id}")
	public ResponseEntity<String> AddImage(@PathVariable("id") Integer id,
			@RequestParam("userImage") MultipartFile file) throws IOException{
		
		String uploadImage = fileService.uploadImage(file, ImagePath);
		EmployeeDto emp = employeeService.getById(id);
		emp.setImg(uploadImage);
		
		employeeService.updateEmp(emp, id);
		return new ResponseEntity<String>(uploadImage, HttpStatus.OK);
	}
	
	@GetMapping("/Image/{id}")
	public void getImage(@PathVariable("id") Integer id,HttpServletResponse response) throws IOException{
		EmployeeDto emp = employeeService.getById(id);
		String image = emp.getImg();
		InputStream resource = fileService.getResource(image,ImagePath);
		//response will be of JPEG type content type
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}

}
