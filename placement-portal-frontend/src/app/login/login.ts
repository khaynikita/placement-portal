import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AuthService } from '../services/auth';

@Component({
  selector: 'app-login',
  imports: [FormsModule, CommonModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {

  private authService = inject(AuthService);

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

          this.message =
            'Login successful';

        },

        error: (error) => {

          console.error(error);

          this.message =
            'Invalid credentials';

        }

      });

  }

}