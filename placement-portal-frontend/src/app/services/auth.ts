// auth.service.ts

import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private http = inject(HttpClient);

  private apiUrl = 'http://localhost:8080/auth';

  register(userData: any): Observable<any> {

    return this.http.post(
      `${this.apiUrl}/register`,
      userData
    );
  }

  login(loginData: any): Observable<any> {

    return this.http.post(
      `${this.apiUrl}/login`,
      loginData
    );
  }

  getCurrentUser(): Observable<any> {

    return this.http.get(
      `${this.apiUrl}/me`
    );
  }
}