import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [FormsModule],
   standalone: true,
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login {

    email = "";
    password = "";

    constructor(
  private authService: AuthService,
  private router: Router
) {}

   login() {

  this.authService.login({
    email: this.email,
    password: this.password
  }).subscribe({

    next: (token) => {

      localStorage.setItem("token",token);

      alert("Login Successful");

      this.router.navigate(['/products']);

    },

    error: () => {

      alert("Invalid Email or Password");

    }

  });

}

}