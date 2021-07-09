import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable()
export class AuthFilter implements HttpInterceptor {

intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
  const user = JSON.parse(localStorage.getItem('user')||'[]');
  console.log("inside the filter:  "+user.userName+" token "+user.jwt )
  if (!user || !user.jwt) {
    console.log("errororo handler");
    return next.handle(req);
  }
  const headers = req.clone({
    headers: req.headers.set('Authorization', `Bearer ${user.jwt}`)
    });
  return next.handle(headers);
  }
}