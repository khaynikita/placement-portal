import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

import { JobCard } from '../job-card/job-card';
import { JobService } from '../../services/job';

import { Job } from '../../model/job.model';

@Component({
  selector: 'app-jobs',
  standalone: true,
  imports: [CommonModule, JobCard],
  templateUrl: './jobs.html',
  styleUrls: ['./jobs.css'],
})
export class Jobs implements OnInit {

  private jobService = inject(JobService);

  jobs: Job[] = [];

  ngOnInit(): void {

    this.loadJobs();

  }

  loadJobs() {

    this.jobService.getJobs().subscribe({

      next: (response) => {

        console.log(response);

        this.jobs = response;

      },

      error: (error) => {

        console.error(error);

      }

    });

  }

}