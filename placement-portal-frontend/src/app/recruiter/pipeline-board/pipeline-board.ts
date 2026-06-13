import { CommonModule } from '@angular/common';
import { Component, inject, OnInit, signal } from '@angular/core';
import { RecruiterService } from '../../services/recruiter';

@Component({
  selector: 'app-pipeline-board',
  imports: [CommonModule],
  templateUrl: './pipeline-board.html',
  styleUrl: './pipeline-board.css',
})
export class PipelineBoard implements OnInit {
  private recruiterService = inject(RecruiterService);
  applicants = signal<any[]>([]);
  statuses = ['APPLIED', 'SHORTLISTED', 'INTERVIEW', 'OFFERED', 'REJECTED'];

  ngOnInit(): void {
    this.loadApplicants();
  }

  loadApplicants() {
    this.recruiterService.getApplicants().subscribe({
      next: response => this.applicants.set(response),
      error: error => console.error(error)
    });
  }

  byStatus(status: string) {
    return this.applicants().filter(applicant => applicant.status === status);
  }

  move(applicationId: string, status: string) {
    const previous = this.applicants();
    this.applicants.set(previous.map(app => app.id === applicationId ? { ...app, status } : app));
    this.recruiterService.updateApplicationStatus(applicationId, status).subscribe({
      error: error => {
        console.error(error);
        this.applicants.set(previous);
      }
    });
  }
}
