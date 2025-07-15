import { Injectable } from '@angular/core';
@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {
 private TOKEN_KEY = 'jwt_token';
  constructor() { }

    public saveToken(jwt: string): void {
    window.localStorage.setItem(this.TOKEN_KEY, jwt);
  }

  getToken(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  clearToken() {
    localStorage.removeItem(this.TOKEN_KEY);
  }


  signOut(){
    
  }

}
