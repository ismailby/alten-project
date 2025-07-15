import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TokenStorageService } from '../../services/token-storage.service';
import { tap } from 'rxjs';
import { environment } from 'environments/environment';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private apiUrl = environment.baseUrl;

  constructor(private http: HttpClient, private tokenStorage: TokenStorageService) {}

  login(credentials: { email: any; password: any }) {
    return this.http.post(`${this.apiUrl}/auth/login`, credentials).pipe(
      tap((res: any) => this.tokenStorage.saveToken(res.token))
    );
  }

  register(data: any) {
    return this.http.post(`${this.apiUrl}/auth/register`, data);
  }

  logout() {
    this.tokenStorage.signOut();
  }
}
