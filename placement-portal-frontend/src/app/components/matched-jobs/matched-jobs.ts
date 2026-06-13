import { CommonModule } from '@angular/common';
import { Component, inject, OnInit, signal } from '@angular/core';
import { RouterLink } from '@angular/router';
import { JobService } from '../../services/job';

@Component({
  selector: 'app-matched-jobs',
  imports: [CommonModule, RouterLink],
  templateUrl: './matched-jobs.html',
  styleUrl: './matched-jobs.css',
})
export class MatchedJobs implements OnInit {
  private jobService = inject(JobService);
  jobs = signal<any[]>([]);
  onlyQualified = signal(false);

  ngOnInit(): void {
    this.jobService.getStudentMatchedJobs().subscribe({
      next: response => this.jobs.set(response),
      error: error => console.error(error)
    });
  }

  visibleJobs() {
    return this.onlyQualified()
      ? this.jobs().filter(item => item.match.score >= 60)
      : this.jobs();
  }

  badgeClass(score: number) {
    return score >= 75 ? 'good' : score >= 50 ? 'ok' : 'low';
  }
}
