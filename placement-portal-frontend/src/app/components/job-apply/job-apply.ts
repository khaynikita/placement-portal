import { CommonModule } from '@angular/common';
import { Component, inject, OnInit, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { Job } from '../../model/job.model';
import { ApplicationService } from '../../services/application';
import { AuthService } from '../../services/auth';
import { JobService } from '../../services/job';

@Component({
  selector: 'app-job-apply',
  imports: [CommonModule, FormsModule],
  templateUrl: './job-apply.html',
  styleUrl: './job-apply.css',
})
export class JobApply implements OnInit {

  private route = inject(ActivatedRoute);
  private router = inject(Router);
  private jobService = inject(JobService);
  private applicationService = inject(ApplicationService);
  private authService = inject(AuthService);

  job = signal<Job | null>(null);
  message = '';

  applicationData = {
    applicantName: '',
    email: '',
    phone: '',
    resumeUrl: '',
    coverLetter: ''
  };

  ngOnInit(): void {
    const currentUser = this.authService.getStoredUser();

    if (currentUser) {
      this.applicationData.applicantName = currentUser.name;
      this.applicationData.email = currentUser.email;
    }

    const jobId = this.route.snapshot.paramMap.get('id');

    if (!jobId) {
      return;
    }

    this.jobService.getJobById(jobId).subscribe({
      next: (response) => this.job.set(response),
      error: (error) => console.error(error)
    });
  }

  submitApplication(): void {
    const job = this.job();
    const currentUser = this.authService.getStoredUser();

    if (!job || !currentUser) {
      this.message = 'Please login before applying.';
      return;
    }

    this.applicationService.applyToJob({
      jobId: job.id,
      studentId: currentUser.userId,
      applicantName: this.applicationData.applicantName,
      email: this.applicationData.email,
      phone: this.applicationData.phone,
      resumeUrl: this.applicationData.resumeUrl,
      coverLetter: this.applicationData.coverLetter
    }).subscribe({
      next: () => {
        this.message = 'Application submitted successfully';
        this.router.navigate(['/applications']);
      },
      error: (error) => {
        console.error(error);
        this.message = 'Application failed';
      }
    });
  }
}
