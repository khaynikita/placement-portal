import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AuthService } from '../services/auth';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [FormsModule, CommonModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {

  private authService = inject(AuthService);
  private router = inject(Router);

  loginData = {

    email: '',
    password: ''

  };

  message = '';

  loginUser() {

    this.authService
      .login(this.loginData)
      .subscribe({

        next: (response) => {

          console.log(response);

          this.authService.saveSession(response);

          this.message =
            'Login successful';

          this.router.navigate(['/']);

        },

        error: (error) => {

          console.error(error);

          this.message =
            'Invalid credentials';

        }

      });

  }

}
