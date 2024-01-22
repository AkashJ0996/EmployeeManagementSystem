import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { CreateemployeeComponent } from './createemployee/createemployee.component';
import { UpdateEmployeeComponent } from './update-employee/update-employee.component';
import { HomepageComponent } from './homepage/homepage.component';
import { LoginComponent } from './login/login.component';



const routes: Routes = [
    {path:'emplist', component:EmployeeListComponent},
    {path:'login',component:LoginComponent},
    {path:'',component:HomepageComponent},
    {path:'add', component:CreateemployeeComponent},
    {path:'update',component:UpdateEmployeeComponent},


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
