import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import {api, storage} from '../../environments/environment';
import {OauthToken} from '../model/oauth-token';
import {User} from '../model/user';
import {Router} from '@angular/router';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
  private currentUser: BehaviorSubject<string>;
  public currentUser$: Observable<string>;

  constructor(private http: HttpClient,
              private router: Router) {

    this.currentUser = new BehaviorSubject<string>(sessionStorage.getItem(storage.name));
    this.currentUser$ = this.currentUser.asObservable();
  }

  public get token(): string {
    return this.currentUser.value;
  }

  isLoggedIn() {
    return this.currentUser.value;
  }

  login(user: User) {
    return this.http.post<OauthToken>(api.url + api.auth.urlAuth, user)
      .pipe(map(oauthToken => {
        // Login successful if there's a token in the response
        if (oauthToken && oauthToken.token) {
          // Store user details and jwt token in local storage to keep user logged in between page refreshes
          sessionStorage.setItem(storage.name, oauthToken.token);

          // Grab OAuth Token from Response
          this.currentUser.next(oauthToken.token);
        }

        return oauthToken;
      }));
  }

  logout() {
    // Remove user from local storage to log user out
    sessionStorage.removeItem(storage.name);
    this.currentUser.next(null);
    this.router.navigate([api.auth.urlLogin]);
  }
}
