// cart.service.ts
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'environments/environment';
import { CartItem } from './cart.model';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class CartService {
  private readonly API_URL = environment.baseUrl+'/cart';

  constructor(private http: HttpClient) {}

  addToCart(productId: number, quantity: number) {
    return this.http.post(this.API_URL, { productId, quantity });
  }

  removeFromCart(productId: number) {
    return this.http.delete(`${this.API_URL}/${productId}`);
  }

  clearCart() {
    return this.http.delete(this.API_URL);
  }

  getCart(): Observable<CartItem[]> {
  return this.http.get<CartItem[]>(this.API_URL);
}

}
