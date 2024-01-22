import { Component, OnInit } from '@angular/core';
import { Employee } from '../employee';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent  {

  employee:Employee = new Employee();

  email :string;
  password : string;

  constructor(private http:HttpClient , private route : Router ,private authService: AuthService){}

login(){
  console.log(this.email +"\n"+ this.password);

  let bodyData = {
      username : this.email ,
      password : this.password 

  };

  this.http.post("http://localhost:8081/login/emp/login", bodyData, { observe: 'response' }).subscribe(
  (response: any) => {
    console.log(response);

    if (response.status === 200 && response.body.message === 'Login Successful') {
      console.log("Login successful. Navigating to emplist.");
      this.authService.signIn();  // Call signIn method upon successful login
      this.route.navigate(['emplist']);
    } else {
      console.log("Login failed. Showing alert.");
      alert("Login failed, please check your email and password");
    }
  },
  (error) => {
    console.error(error);
    alert("An error occurred while processing your request.");
  }
);
}
 

}
