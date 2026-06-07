// admin.service.ts

import { Injectable, inject } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  private http = inject(HttpClient);

  private apiUrl =
    'http://localhost:8080/api/admin';

  getDashboard(): Observable<any> {

    return this.http.get<any>(
      `${this.apiUrl}/dashboard`
        .replace('/dashboard', '/dashboard/stats')
    );

  }

  getAllUsers(): Observable<any> {

    return this.http.get<any>(
      `${this.apiUrl}/users`
    );

  }

  deleteUser(
    id: string
  ): Observable<any> {

    return this.http.delete<any>(
      `${this.apiUrl}/users/${id}`
    );

  }

  getAllJobs(): Observable<any> {

    return this.http.get<any>(
      `${this.apiUrl}/jobs`
    );

  }

}
