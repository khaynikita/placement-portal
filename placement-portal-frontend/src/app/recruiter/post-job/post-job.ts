// post-job.ts

import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { JobService } from '../../services/job';

@Component({
  selector: 'app-post-job',
  imports: [FormsModule],
  templateUrl: './post-job.html',
  styleUrl: './post-job.css',
})
export class PostJob {

  private jobService = inject(JobService);

  jobData = {

    title: '',
    companyName: '',
    location: '',
    salary: '',
    jobType: '',
    description: '',
    requirements: '',
    experienceRequired: '',
    skills: []

  };

  createJob() {

    this.jobService
      .createJob(this.jobData)
      .subscribe({

        next: (response) => {

          console.log(response);

          alert('Job created successfully');

        },

        error: (error) => {

          console.error(error);

        }

      });

  }

}