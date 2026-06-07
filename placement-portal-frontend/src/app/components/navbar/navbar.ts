import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth';
import { CommonModule } from '@angular/common';
import { NotificationBell } from '../notification-bell/notification-bell';

@Component({
  selector: 'app-navbar',
  imports: [RouterModule, CommonModule, NotificationBell],
  templateUrl: './navbar.html',
  styleUrl: './navbar.css',
})
export class Navbar {
  constructor(
    private router: Router,
    private authService: AuthService
  ) {}

  // You can later connect this with user API
  user = {
    name: 'Nikita',
    profileImage: 'https://i.pravatar.cc/40'
  };

  get isLoggedIn() {
    return !!this.authService.getToken();
  }

  get role() {
    return this.authService.getCurrentUserRole();
  }

  logout() {

    this.authService.logout();

    this.router.navigate(['/login']);

  }

}
