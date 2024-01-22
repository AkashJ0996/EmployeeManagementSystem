import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { HttpClientModule } from '@angular/common/http';
import { CreateemployeeComponent } from './createemployee/createemployee.component';
import { FormsModule } from '@angular/forms';
import { UpdateEmployeeComponent } from './update-employee/update-employee.component';
import { HomepageComponent } from './homepage/homepage.component';
import { LoginComponent } from './login/login.component';



@NgModule({
  declarations: [
    AppComponent,
    EmployeeListComponent,
    CreateemployeeComponent,
    UpdateEmployeeComponent,
    HomepageComponent,
    LoginComponent,


  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
