import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Employee } from './employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private baseURL ="http://localhost:8081/emp/emplist";
  id:number;
  
  constructor(private httpClient:HttpClient) { }

  getEmployeeList():Observable<Employee[]>{
    return this.httpClient.get<Employee[]>( this.baseURL );
  }
  
  createEmployee(employee:Employee):Observable<Object>{
    return this.httpClient.post<Object>( (`http://localhost:8081/emp/signUp`) ,employee);
  }

 

  getId(getId:number){
    this.id=getId;
  }

  getEmployeeById():Observable<Employee>{
    return this.httpClient.get<Employee>((`http://localhost:8081/emp/getid/${this.id}`));
  }

  updateEmployeeById(employee:Employee):Observable<Object>{
    return this.httpClient.put<Object>((`http://localhost:8081/emp/updateEmp/${this.id}`), employee);
  } 

  deleteEmployeeById(id:number):Observable<Object>{
    return this.httpClient.delete<Object>((`http://localhost:8081/emp/delete/${id}`));
  } 


}
