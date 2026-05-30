// dashboard.ts

import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { JobService } from '../../services/job';

@Component({
  selector: 'app-dashboard',
  imports: [CommonModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard implements OnInit {

  private jobService = inject(JobService);

  recentJobs: any[] = [];

  ngOnInit(): void {

    this.loadRecentJobs();

  }

  loadRecentJobs() {

    this.jobService.getJobs().subscribe({

      next: (response) => {

        this.recentJobs = response;

      }

    });

  }

}