import { Component, OnInit } from '@angular/core';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-update-employee',
  templateUrl: './update-employee.component.html',
  styleUrl: './update-employee.component.css'
})
export class UpdateEmployeeComponent implements OnInit {

  employee: Employee = new Employee() ;

  constructor(private employeeService:EmployeeService , private router:Router){
 }
  ngOnInit(): void {
    this.getEmployeeById();
  
  }
  
 
  

  getEmployeeById() {
    this.employeeService.getEmployeeById().subscribe(
      data => {
        this.employee = data;
      },
      error => {
        console.error('Error fetching employee details:', error);
        // Handle error, e.g., show a user-friendly message or redirect
      }
    );
  }

  updateEmployee(){
    console.log(this.employee);
    this.employeeService.updateEmployeeById(this.employee).subscribe();
    this.router.navigate(['/emplist']);
  }


}
