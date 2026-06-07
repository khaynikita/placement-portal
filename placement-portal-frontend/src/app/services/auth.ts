// auth.service.ts

import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface AuthResponse {
  token: string;
  userId: string;
  name: string;
  email: string;
  role: string;
}

export interface CurrentUser {
  userId: string;
  name: string;
  email: string;
  role: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private http = inject(HttpClient);

  private apiUrl = 'http://localhost:8080/auth';
  private tokenKey = 'token';
  private userKey = 'currentUser';

  register(userData: any): Observable<AuthResponse> {

    return this.http.post(
      `${this.apiUrl}/register`,
      userData
    ) as Observable<AuthResponse>;
  }

  login(loginData: any): Observable<AuthResponse> {

    return this.http.post(
      `${this.apiUrl}/login`,
      loginData
    ) as Observable<AuthResponse>;
  }

  getCurrentUser(): Observable<AuthResponse> {

    return this.http.get(
      `${this.apiUrl}/me`
    ) as Observable<AuthResponse>;
  }

  saveSession(response: AuthResponse): void {
    localStorage.setItem(this.tokenKey, response.token);
    localStorage.setItem(this.userKey, JSON.stringify({
      userId: response.userId,
      name: response.name,
      email: response.email,
      role: response.role
    }));
  }

  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  getStoredUser(): CurrentUser | null {
    const user = localStorage.getItem(this.userKey);

    if (!user) {
      return null;
    }

    return JSON.parse(user) as CurrentUser;
  }

  getCurrentUserRole(): string {
    const user = this.getStoredUser();

    if (user?.role) {
      return user.role;
    }

    const token = this.getToken();

    if (!token) {
      return '';
    }

    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      return payload.role || '';
    } catch {
      return '';
    }
  }

  logout(): void {
    localStorage.removeItem(this.tokenKey);
    localStorage.removeItem(this.userKey);
  }
}
