import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/api';

  getStudentStats(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/student/dashboard/stats`);
  }

  getRecruiterStats(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/recruiter/dashboard/stats`);
  }

  getAdminStats(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/admin/dashboard/stats`);
  }
}
