import { Routes } from '@angular/router';

import { Dashboard } from './components/dashboard/dashboard';
import { Jobs } from './components/jobs/jobs';
import { Applications } from './components/applications/applications';
import { Admin } from './components/admin/admin';

import { Login } from './login/login';
import { Register } from './register/register';

import { authGuard } from './guards/auth-guard';

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
    component: Jobs,
    canActivate: [authGuard]
  },

  {
    path: 'applications',
    component: Applications,
    canActivate: [authGuard]
  },

  {
    path: 'admin',
    component: Admin,
    canActivate: [authGuard]
  }

];