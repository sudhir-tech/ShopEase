import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { UserService } from '../../services/user';

@Component({
  selector: 'app-register',
  imports: [FormsModule, RouterLink],
   standalone: true,
  templateUrl: './register.html',
  styleUrl: './register.css'
})
export class Register {

  user = {
    name: '',
    email: '',
    password: '',
    role: 'USER'
  };

  constructor(
    private userService: UserService,
    private router: Router
  ) {}

  register() {

    this.userService.register(this.user).subscribe({

      next: () => {

        alert("Registration Successful");

        this.router.navigate(['/login']);

      },

      error: (error) => {
  console.log(error);
  alert(JSON.stringify(error.error));
   }

    });

  }

}