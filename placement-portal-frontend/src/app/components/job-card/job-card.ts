import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

import { Job } from '../../model/job.model';

@Component({
  selector: 'app-job-card',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './job-card.html',
  styleUrls: ['./job-card.css'],
})
export class JobCard {

  @Input() job!: Job;

}