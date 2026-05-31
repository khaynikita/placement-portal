import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const isPublicJobsRead =
    req.method === 'GET' &&
    req.url.startsWith('http://localhost:8080/jobs');
  const isAuthRequest = req.url.startsWith('http://localhost:8080/auth');

  if (isPublicJobsRead || isAuthRequest) {
    return next(req);
  }

  const token = localStorage.getItem('token');

  if (!token) {
    return next(req);
  }

  return next(req.clone({
    setHeaders: {
      Authorization: `Bearer ${token}`
    }
  }));
};
