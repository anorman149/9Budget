import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import {AuthenticationService} from "../service/authentication.service";
import {api} from "../../environments/environment";

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService
  ) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (this.authenticationService.isLoggedIn()) { //TODO comment and log
      //Authorized

      return true;
    }

    //Not logged in so redirect to login page with the return url
    this.router.navigate([api.auth.urlLogin], { queryParams: { returnUrl: state.url }});

    return false;
  }
}
