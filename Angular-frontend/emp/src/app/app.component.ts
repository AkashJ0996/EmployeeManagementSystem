import { ChangeDetectorRef, Component } from '@angular/core';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {


  constructor(private authService: AuthService , private router:Router) {
    console.log('NavbarComponent - AuthService injected:', authService);
  }



  // Getter to check if the user is authenticated
  get isLoggedIn(): boolean {
    const loggedIn = this.authService.getAuthenticationStatus();
    console.log('isLoggedIn:', loggedIn);
    return loggedIn;
  }

  signOut(): void{
    this.authService.signOut();
    this.router.navigate(['/']);

  }



}
