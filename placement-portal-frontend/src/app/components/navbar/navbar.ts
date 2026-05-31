import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth';

@Component({
  selector: 'app-navbar',
  imports: [RouterModule],
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

  logout() {

    this.authService.logout();

    this.router.navigate(['/login']);

  }

}
