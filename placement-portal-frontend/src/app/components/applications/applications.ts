// applications.ts

import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApplicationService } from '../../services/application';

@Component({
  selector: 'app-applications',
  imports: [CommonModule, FormsModule],
  templateUrl: './applications.html',
  styleUrl: './applications.css',
})
export class Applications implements OnInit {

  private applicationService =
    inject(ApplicationService);

  applications: any[] = [];
  statusFilter = '';

  ngOnInit(): void {

    this.getApplications();

  }

  getApplications() {

    this.applicationService
      .getCurrentUserApplications()
      .subscribe({

        next: (response) => {

          this.applications = response;

        },

        error: (error) => {

          console.error(error);

        }

      });

  }

  get filteredApplications() {
    if (!this.statusFilter) {
      return this.applications;
    }

    return this.applications.filter(application => application.status === this.statusFilter);
  }

  countByStatus(status: string) {
    return this.applications.filter(application => application.status === status).length;
  }

  withdraw(applicationId: string) {
    this.applicationService.withdrawApplication(applicationId).subscribe({
      next: () => this.getApplications(),
      error: error => console.error(error)
    });
  }

}
