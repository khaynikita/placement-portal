import { CommonModule } from '@angular/common';
import { Component, inject, OnInit, signal } from '@angular/core';
import { InterviewService } from '../../services/interview';

@Component({
  selector: 'app-upcoming-interviews',
  imports: [CommonModule],
  templateUrl: './upcoming-interviews.html',
  styleUrl: './upcoming-interviews.css',
})
export class UpcomingInterviews implements OnInit {
  private interviewService = inject(InterviewService);
  interviews = signal<any[]>([]);

  ngOnInit(): void {
    this.interviewService.getUpcoming().subscribe({
      next: response => this.interviews.set(response),
      error: error => console.error(error)
    });
  }
}
