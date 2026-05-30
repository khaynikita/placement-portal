// profile.ts

import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserService } from '../../services/user';

@Component({
  selector: 'app-profile',
  imports: [CommonModule],
  templateUrl: './profile.html',
  styleUrl: './profile.css',
})
export class Profile implements OnInit {

  private userService = inject(UserService);

  profile: any;

  ngOnInit(): void {

    this.getProfile();

  }

  getProfile() {

    this.userService.getProfile().subscribe({

      next: (response) => {

        this.profile = response;

      }

    });

  }

}