import { CommonModule } from '@angular/common';
import { Component, inject, OnInit, signal } from '@angular/core';
import { RecruiterService } from '../../services/recruiter';

@Component({
  selector: 'app-applicants-list',
  imports: [CommonModule],
  templateUrl: './applicants-list.html',
  styleUrl: './applicants-list.css',
})
export class ApplicantsList implements OnInit {

  private recruiterService = inject(RecruiterService);

  applicants = signal<any[]>([]);

  ngOnInit(): void {
    this.loadApplicants();
  }

  loadApplicants() {
    this.recruiterService.getApplicants().subscribe({
      next: response => this.applicants.set(Array.isArray(response) ? response : []),
      error: error => console.error(error)
    });
  }

  updateStatus(applicationId: string, status: string) {
    this.recruiterService.updateApplicationStatus(applicationId, status).subscribe({
      next: () => this.loadApplicants(),
      error: error => console.error(error)
    });
  }

}
