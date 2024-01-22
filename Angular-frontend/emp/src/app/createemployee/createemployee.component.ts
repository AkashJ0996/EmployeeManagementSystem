import { Component, OnInit } from '@angular/core';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-createemployee',
  templateUrl: './createemployee.component.html',
  styleUrl: './createemployee.component.css'
})
export class CreateemployeeComponent implements OnInit{

  employee : Employee = new Employee();

  constructor(private employeeService: EmployeeService , private router:Router){}
  ngOnInit(): void {

  }

  saveEmployee(){
    this.employeeService.createEmployee(this.employee).subscribe(data=>{
      console.log(data);
      this.goTohome();
      alert("Record added successfully ..")
    },

    error => console.log(error));
  }

  goTohome(){
    this.router.navigate(['/']);
  }

  onSubmit(){
  
    this.saveEmployee();
  }
}
