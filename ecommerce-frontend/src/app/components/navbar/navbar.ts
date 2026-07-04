import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './navbar.html',
  styleUrl: './navbar.css',
})
export class Navbar {
constructor(private router: Router) {}
isLoggedIn(): boolean {

    return localStorage.getItem('token') !== null;

  }
logout() {
  localStorage.removeItem('token');
  alert('Logged out successfully');
  this.router.navigate(['/login']);
}
}
