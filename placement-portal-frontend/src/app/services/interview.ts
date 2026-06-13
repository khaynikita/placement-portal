import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InterviewService {
  private http = inject(HttpClient);

  scheduleInterview(data: any): Observable<any> {
    return this.http.post<any>('http://localhost:8080/api/recruiter/interviews', data);
  }

  getUpcoming(): Observable<any[]> {
    return this.http.get<any[]>('http://localhost:8080/api/student/interviews/upcoming');
  }
}
