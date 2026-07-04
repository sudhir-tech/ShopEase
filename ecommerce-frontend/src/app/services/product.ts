import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from '../models/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private apiUrl = 'http://localhost:8080/products';

  constructor(private http: HttpClient) {}

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.apiUrl);
  }

  addProduct(product: Product): Observable<Product> {
    return this.http.post<Product>(this.apiUrl, product);
  }

  updateProduct(id: number, product: Product): Observable<Product> {
  return this.http.put<Product>(this.apiUrl + "/" + id, product);
}

  deleteProduct(id: number): Observable<string> {
  return this.http.delete(this.apiUrl + "/" + id, {
    responseType: 'text'
  });
}
searchProducts(name: string): Observable<Product[]> {
  return this.http.get<Product[]>(this.apiUrl + "/search?name=" + name);
}



}