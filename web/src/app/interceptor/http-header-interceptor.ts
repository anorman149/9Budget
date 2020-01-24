import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {AuthenticationService} from '../service/authentication.service';
import {Observable} from 'rxjs';

@Injectable({ providedIn: 'root' })
export class HttpHeaderInterceptor implements HttpInterceptor {
  constructor(private authenticationService: AuthenticationService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // Add Auth header with token if available
    const currentUser = this.authenticationService.token;

    // Add Authentication to each Request that comes through
    if (currentUser) {
      request = request.clone({
        withCredentials: true,
        setHeaders: {
          Authorization: `Bearer ${currentUser}`,
          'Content-Type': 'application/json',
          Accept: 'application/json'
        },
      });
    }

    return next.handle(request);
  }
}
