import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AddToCart } from '../models/add-to-cart';
import { CartResponse } from '../models/cart-response';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private api = "http://localhost:8080/cart";

  constructor(private http: HttpClient) {}

  addToCart(dto: AddToCart): Observable<string> {
    return this.http.post(this.api + "/add", dto, {
      responseType: 'text'
    });
  }

  getCart(userId: number): Observable<CartResponse[]> {
    return this.http.get<CartResponse[]>(this.api + "/user/" + userId);
  }

  remove(cartItemId: number): Observable<string> {
    return this.http.delete(this.api + "/remove/" + cartItemId, {
      responseType: 'text'
    });
  }

  placeOrder(userId: number): Observable<string> {
    return this.http.post(this.api + "/place-order/" + userId, {}, {
      responseType: 'text'
    });
  }
}