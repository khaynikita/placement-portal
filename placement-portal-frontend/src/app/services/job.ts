// import { Injectable, inject } from '@angular/core';

// import { HttpClient } from '@angular/common/http';

// import { Observable } from 'rxjs';

// @Injectable({
//   providedIn: 'root'
// })
// export class JobService {

//   private http = inject(HttpClient);

//   private apiUrl = 'http://localhost:8080/jobs';

//   getJobs(): Observable<any> {

//     return this.http.get<any>(this.apiUrl);

//   }
// }
// job.service.ts

import { Injectable, inject } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import { Job } from '../model/job.model';

@Injectable({
  providedIn: 'root'
})
export class JobService {

  private http = inject(HttpClient);

  private apiUrl = 'http://localhost:8080/jobs';

  getJobs(): Observable<Job[]> {

    return this.http.get<Job[]>(this.apiUrl);

  }

  getJobById(id: string): Observable<Job> {

    return this.http.get<Job>(
      `${this.apiUrl}/${id}`
    );

  }

  createJob(jobData: any): Observable<Job> {

    return this.http.post<Job>(
      this.apiUrl,
      jobData
    );

  }

  updateJob(
    id: string,
    jobData: any
  ): Observable<Job> {

    return this.http.put<Job>(
      `${this.apiUrl}/${id}`,
      jobData
    );

  }

  deleteJob(id: string): Observable<any> {

    return this.http.delete<any>(
      `${this.apiUrl}/${id}`
    );

  }

}
