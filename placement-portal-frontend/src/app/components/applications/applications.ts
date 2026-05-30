// applications.ts

import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApplicationService } from '../../services/application';

@Component({
  selector: 'app-applications',
  imports: [CommonModule],
  templateUrl: './applications.html',
  styleUrl: './applications.css',
})
export class Applications implements OnInit {

  private applicationService =
    inject(ApplicationService);

  applications: any[] = [];

  ngOnInit(): void {

    this.getApplications();

  }

  getApplications() {

    this.applicationService
      .getMyApplications('student1')
      .subscribe({

        next: (response) => {

          this.applications = response;

        },

        error: (error) => {

          console.error(error);

        }

      });

  }

}