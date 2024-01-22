import { Component, OnInit } from '@angular/core';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';
import { catchError, tap } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrl: './employee-list.component.css'
})
export class EmployeeListComponent implements OnInit{
 
  emplist : Employee[] ;
 
  constructor(private employeeService:EmployeeService , private router:Router){

  }
 
  ngOnInit(): void {
    this.getEmployees();
  }

  private getEmployees(){
    
    this.employeeService.getEmployeeList().subscribe(
      (data) => {
        this.emplist=data;
      }
      
    );
  }

  updateEmployeeRecord(id:number){
    console.log("id= ",id)
      this.employeeService.getId(id);
      this.router.navigate(['update']);

    }

  deleteEmployeeRecord(id:number){
      this.employeeService.deleteEmployeeById(id).subscribe();
      this.router.navigate(['/']);
      alert("record deleted successfully....")
  }


}
