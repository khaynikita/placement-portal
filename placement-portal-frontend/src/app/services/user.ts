// user.service.ts

import { Injectable, inject } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private http = inject(HttpClient);

  private apiUrl =
    'http://localhost:8080/users';

  getProfile(): Observable<any> {

    return this.http.get<any>(
      `${this.apiUrl}/profile`
    );

  }

  updateProfile(
    profileData: any
  ): Observable<any> {

    return this.http.put<any>(
      `${this.apiUrl}/profile`,
      profileData
    );

  }

  uploadResume(
    formData: FormData
  ): Observable<any> {

    return this.http.post<any>(
      `${this.apiUrl}/resume`,
      formData
    );

  }

}