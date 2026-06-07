import { Routes } from '@angular/router';

import { Dashboard } from './components/dashboard/dashboard';
import { Jobs } from './components/jobs/jobs';
import { JobDetails } from './components/job-details/job-details';
import { JobApply } from './components/job-apply/job-apply';
import { Applications } from './components/applications/applications';
import { Admin } from './components/admin/admin';

import { Login } from './login/login';
import { Register } from './register/register';

import { authGuard } from './guards/auth-guard';
import { roleGuard } from './guards/role.guard';

export const routes: Routes = [

  // PUBLIC ROUTES

  {
    path: 'login',
    component: Login
  },

  {
    path: 'register',
    component: Register
  },

  // PROTECTED ROUTES

  {
    path: '',
    component: Dashboard,
    canActivate: [authGuard]
  },

  {
    path: 'jobs',
    component: Jobs
  },

  {
    path: 'jobs/:id/apply',
    component: JobApply,
    canActivate: [authGuard]
  },

  {
    path: 'jobs/:id',
    component: JobDetails
  },

  {
    path: 'applications',
    component: Applications,
    canActivate: [roleGuard(['STUDENT', 'ADMIN'])]
  },

  {
    path: 'admin',
    component: Admin,
    canActivate: [roleGuard(['ADMIN'])]
  },

  {
    path: 'recruiter/applicants',
    loadComponent: () => import('./recruiter/applicants-list/applicants-list').then(m => m.ApplicantsList),
    canActivate: [roleGuard(['RECRUITER', 'ADMIN'])]
  }

];
