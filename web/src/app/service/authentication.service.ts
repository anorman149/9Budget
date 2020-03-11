import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable, of, ReplaySubject} from 'rxjs';
import {catchError, map, tap} from 'rxjs/operators';
import {api, storage} from '../../environments/environment';
import {OauthToken} from '../model/oauth-token';
import {User} from '../model/user';
import {Router} from '@angular/router';
import {CookieService} from 'ngx-cookie-service';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
  _currentUser: User | null = null;
  private authenticationState = new ReplaySubject<Account | null>(1);
  private currentUser$: Observable<OauthToken>;

  constructor(
    private http: HttpClient,
    private router: Router,
    private cookieService: CookieService
  ) {}

  isLoggedIn(): boolean {
    return this._currentUser !== null;
  }

  login(user: User) {
    this.currentUser$ = this.http.post<OauthToken>(api.url + api.auth.urlAuth, user)
      .pipe(
        catchError(() => {
          return of(null);
      }),
      tap((oauthToken: OauthToken | null) => {
        // Login successful if there's a token in the response
        if (oauthToken && oauthToken.token) {
          // Grab OAuth Token from Response
          this._currentUser = oauthToken.user;
        }

        return oauthToken;
      }));

    return this.currentUser$;
  }

  getAuthenticationState(): Observable<Account | null> {
    return this.authenticationState.asObservable();
  }

  logout() {
    // Remove Cookie to log user out
    this.cookieService.delete(storage.name);

    this._currentUser = null;
    this.router.navigate([api.auth.urlLogin]);
  }

  get currentUser(): User | null {
    return this._currentUser;
  }
}
