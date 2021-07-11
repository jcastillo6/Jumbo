import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../model/user';
import { map } from 'rxjs/operators'; 
import { Emitters } from '../emitters/emitters';

@Injectable({
  providedIn: 'root'
})
export class AccountServiceService {
  private userSubject: BehaviorSubject<User>;
  public user: Observable<User>;
  

  constructor(        private router: Router,
    private http: HttpClient) { 

      this.userSubject = new BehaviorSubject<User>(JSON.parse((localStorage.getItem('user')||'[]')));
      this.user = this.userSubject.asObservable();
    }

  public get userValue(): User {
      return this.userSubject.value;
  }
  

  public login(userName:string, password:string) {
    
    return this.http.post<User>(`${environment.backend}/login`, { userName, password })
        .pipe(map((user: User)=> {
            // store user details and jwt token in local storage to keep user logged in between page refreshes
            user.userName=userName;  
            localStorage.setItem('user', JSON.stringify(user));
            this.userSubject.next(user);
            Emitters.authEmitter.next(true);
            return user;
        }));

  }

  public logout() {
    // remove all data
    localStorage.clear();
    // invalid user
    this.userSubject.next({userName: null,jwt: null});
    this.router.navigate(['/login']);
    Emitters.authEmitter.next(false);
  }


}
