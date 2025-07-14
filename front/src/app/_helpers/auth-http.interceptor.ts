import { Injectable } from '@angular/core';
import {
  HttpInterceptorFn,
  HttpRequest,
  HttpHandlerFn,
  HttpEvent,
  HttpErrorResponse
} from '@angular/common/http';
import { inject } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { TokenStorageService } from 'app/services/token-storage.service';

export const authHttpInterceptor: HttpInterceptorFn = (
  req: HttpRequest<any>,
  next: HttpHandlerFn
): Observable<HttpEvent<any>> => {
  const tokenStorage = inject(TokenStorageService);
  const token = tokenStorage.getToken();

  const authReq = token
    ? req.clone({ headers: req.headers.set('Authorization', 'Bearer ' + token) })
    : req;

  return next(authReq).pipe(
    tap({
      error: (err) => {
        if (err instanceof HttpErrorResponse && err.status === 401) {
          console.error('Erreur 401 - Non autorisé');
        }
      }
    })
  );
};
