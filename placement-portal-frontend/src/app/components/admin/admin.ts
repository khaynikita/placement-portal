// admin.ts

import { Component, inject, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminService } from '../../services/admin';

@Component({
  selector: 'app-admin',
  imports: [CommonModule],
  templateUrl: './admin.html',
  styleUrl: './admin.css',
})
export class Admin implements OnInit {

  private adminService = inject(AdminService);

  dashboardData = signal<any>(null);

  users = signal<any[]>([]);

  ngOnInit(): void {

    this.loadDashboard();

    this.loadUsers();

  }

  loadDashboard() {

    this.adminService.getDashboard().subscribe({

      next: (response) => {

        this.dashboardData.set(response);

      }

    });

  }

  loadUsers() {

    this.adminService.getAllUsers().subscribe({

      next: (response) => {

        this.users.set(response);

      }

    });

  }

  deleteUser(id: string) {

    this.adminService.deleteUser(id).subscribe({

      next: () => {

        this.loadUsers();

      }

    });

  }

}
