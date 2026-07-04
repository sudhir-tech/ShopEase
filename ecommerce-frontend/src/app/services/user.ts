import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private api = "http://localhost:8080/users";

  constructor(private http: HttpClient) {}

  register(user: User): Observable<any> {
    return this.http.post(this.api + "/register", user);
  }

}