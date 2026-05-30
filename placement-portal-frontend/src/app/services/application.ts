// application.service.ts

import { Injectable, inject } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApplicationService {

  private http = inject(HttpClient);

  private apiUrl =
    'http://localhost:8080/applications';

  applyToJob(
    applicationData: any
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

}