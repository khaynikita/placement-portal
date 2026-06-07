// dashboard.ts

import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { JobService } from '../../services/job';
import { DashboardService } from '../../services/dashboard';
import { AuthService } from '../../services/auth';

@Component({
  selector: 'app-dashboard',
  imports: [CommonModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard implements OnInit {

  private jobService = inject(JobService);
  private dashboardService = inject(DashboardService);
  private authService = inject(AuthService);

  recentJobs: any[] = [];
  stats: any;
  role = '';

  ngOnInit(): void {

    this.role = this.authService.getCurrentUserRole() || 'STUDENT';
    this.loadStats();
    this.loadRecentJobs();

  }

  loadStats() {
    const request =
      this.role === 'ADMIN'
        ? this.dashboardService.getAdminStats()
        : this.role === 'RECRUITER'
          ? this.dashboardService.getRecruiterStats()
          : this.dashboardService.getStudentStats();

    request.subscribe({
      next: response => this.stats = response,
      error: error => console.error(error)
    });
  }

  loadRecentJobs() {

    this.jobService.getJobs().subscribe({

      next: (response) => {

        this.recentJobs = response;

      }

    });

  }

}
