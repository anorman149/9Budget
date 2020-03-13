import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {api, storage} from '../../environments/environment';
import {OauthToken} from '../model/oauth-token';
import {User} from '../model/user';
import {Router} from '@angular/router';
import {CookieService} from 'ngx-cookie-service';

@Injectable({providedIn: 'root'})
export class AuthenticationService {
  private currentUser: BehaviorSubject<String>;
  public currentUser$: Observable<String>;

  constructor(
    private http: HttpClient,
    private router: Router,
    private cookieService: CookieService
  ) {
    this.currentUser = new BehaviorSubject<String>(this.cookieService.get(storage.name));
    this.currentUser$ = this.currentUser.asObservable();
  }

  public get token(): String {
    return this.currentUser.value;
  }

  isLoggedIn() {
    return this.currentUser.value;
  }

  login(user: User) {
    return this.http.post<OauthToken>(api.url + api.auth.urlAuth, user)
      .pipe(map(oauthToken => {
        //Login successful if there's a token in the response
        if (oauthToken && oauthToken.token) {
          //Grab OAuth Token from Response
          this.currentUser.next(oauthToken.token);
        }

        return oauthToken;
      }));
  }

  logout() {
    // Remove Cookie to log user out
    this.cookieService.delete(storage.name);

    this.currentUser.next(null);
    this.router.navigate([api.auth.urlLogin]);
  }
}
