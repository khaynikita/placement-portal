// recruiter.service.ts

import { Injectable, inject } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RecruiterService {

  private http = inject(HttpClient);

  private apiUrl =
    'http://localhost:8080/recruiter';

  getDashboard(): Observable<any> {

    return this.http.get<any>(
      `${this.apiUrl}/dashboard`
    );

  }

  getRecruiterJobs(): Observable<any> {

    return this.http.get<any>(
      `${this.apiUrl}/jobs`
    );

  }

  getApplicants(
    jobId?: string
  ): Observable<any> {

    return this.http.get<any>(
      'http://localhost:8080/api/recruiter/applicants'
    );

  }

  updateApplicationStatus(
    applicationId: string,
    status: string
  ): Observable<any> {

    return this.http.patch<any>(
      `http://localhost:8080/api/recruiter/applications/${applicationId}/status`,
      { status }
    );

  }

}
