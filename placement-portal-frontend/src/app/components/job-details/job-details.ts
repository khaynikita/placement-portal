import { CommonModule } from '@angular/common';
import { Component, inject, OnInit, signal } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';

import { Job } from '../../model/job.model';
import { JobService } from '../../services/job';

@Component({
  selector: 'app-job-details',
  imports: [CommonModule, RouterLink],
  templateUrl: './job-details.html',
  styleUrl: './job-details.css',
})
export class JobDetails implements OnInit {

  private route = inject(ActivatedRoute);
  private jobService = inject(JobService);

  job = signal<Job | null>(null);

  ngOnInit(): void {
    const jobId = this.route.snapshot.paramMap.get('id');

    if (!jobId) {
      return;
    }

    this.jobService.getJobById(jobId).subscribe({
      next: (response) => this.job.set(response),
      error: (error) => console.error(error)
    });
  }
}
