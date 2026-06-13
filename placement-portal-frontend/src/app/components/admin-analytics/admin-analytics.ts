import { CommonModule } from '@angular/common';
import { Component, inject, OnInit, signal } from '@angular/core';
import { AnalyticsService } from '../../services/analytics';

@Component({
  selector: 'app-admin-analytics',
  imports: [CommonModule],
  templateUrl: './admin-analytics.html',
  styleUrl: './admin-analytics.css',
})
export class AdminAnalytics implements OnInit {
  private analyticsService = inject(AnalyticsService);
  analytics = signal<any>(null);

  ngOnInit(): void {
    this.analyticsService.getAnalytics().subscribe({
      next: response => this.analytics.set(response),
      error: error => console.error(error)
    });
  }

  exportReport() {
    this.analyticsService.exportReport().subscribe(blob => {
      const url = URL.createObjectURL(blob);
      const anchor = document.createElement('a');
      anchor.href = url;
      anchor.download = 'placement-analytics.csv';
      anchor.click();
      URL.revokeObjectURL(url);
    });
  }
}
