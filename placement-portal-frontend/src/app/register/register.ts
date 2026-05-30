import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AuthService } from '../services/auth';
import { Router } from '@angular/router';
@Component({
  selector: 'app-register',
  imports: [FormsModule, CommonModule],
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class Register {

  private authService = inject(AuthService);
  private router = inject(Router);

  registerData = {

    name: '',
    email: '',
    password: '',
    role: 'STUDENT'

  };

  message = '';

  registerUser() {

  this.authService
    .register(this.registerData)
    .subscribe({

      next: () => {

        // auto login after register
        this.authService.login({

          email: this.registerData.email,
          password: this.registerData.password

        }).subscribe({

          next: (loginResponse: any) => {

            console.log(loginResponse);

            // save JWT token
            localStorage.setItem(
              'token',
              loginResponse.token
            );

            // redirect to dashboard
            this.router.navigate(['/']);

          },

          error: (error) => {

            console.error(error);

            this.message =
              'Registered but login failed';

          }

        });

      },

      error: (error) => {

        console.error(error);

        this.message =
          'Registration failed';

      }

    });

}

}