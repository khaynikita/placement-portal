import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-navbar',
  imports: [RouterModule],
  templateUrl: './navbar.html',
  styleUrl: './navbar.css',
})
export class Navbar {
  constructor(private router: Router) {}

  // You can later connect this with user API
  user = {
    name: 'Nikita',
    profileImage: 'https://i.pravatar.cc/40'
  };

  logout() {

    localStorage.removeItem('token');

    this.router.navigate(['/login']);

  }

}
