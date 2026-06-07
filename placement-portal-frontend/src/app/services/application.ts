// application.service.ts

import { Injectable, inject } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

export interface ApplyJobRequest {
  jobId: string;
  studentId: string;
  applicantName: string;
  email: string;
  phone: string;
  resumeUrl: string;
  coverLetter: string;
}

@Injectable({
  providedIn: 'root'
})
export class ApplicationService {

  private http = inject(HttpClient);

  private apiUrl =
    'http://localhost:8080/applications';

  applyToJob(
    applicationData: ApplyJobRequest
  ): Observable<any> {

    return this.http.post<any>(
      this.apiUrl,
      applicationData
    );

  }

  getMyApplications(
    studentId: string
  ): Observable<any> {

    return this.http.get<any>(
      `${this.apiUrl}/student/${studentId}`
    );

  }

  getCurrentUserApplications(): Observable<any[]> {

    return this.http.get<any[]>(
      'http://localhost:8080/api/student/applications'
    );

  }

  withdrawApplication(applicationId: string): Observable<any> {

    return this.http.patch<any>(
      `http://localhost:8080/api/student/applications/${applicationId}/withdraw`,
      {}
    );

  }

}
