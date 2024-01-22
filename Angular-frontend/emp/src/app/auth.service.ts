import {  Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private isLoggedIn = false;

  constructor( ){}
  // Getter to retrieve authentication status
  getAuthenticationStatus(): boolean {
    return this.isLoggedIn;
  }
 
  signIn(): void {
    console.log('Attempting to sign in...');
    this.isLoggedIn = true;
    console.log('User signed in. isLoggedIn:', this.isLoggedIn);
  }

  signOut(): void {
    this.isLoggedIn = false;
    console.log('User signed out. isLoggedIn:', this.isLoggedIn);
    alert("Sign-Out Succeessfully");
  }

}
