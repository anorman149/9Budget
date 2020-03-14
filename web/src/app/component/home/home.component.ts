import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from '../../service/authentication.service';
import {api} from "../../../environments/environment";
import {Keepalive} from "@ng-idle/keepalive";
import {Router} from "@angular/router";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  private collapsed = true; // Start collapsed Navigation
  private showBudgets = false;
  private showDashboard = true; // Default View
  private showTransactions = false;
  private showAccount = false;
  private showAPI = false;
  private isLoggedIn = true;

  constructor(private authenticationService: AuthenticationService,
              private router: Router) {}

  ngOnInit() {
    // Check if logged in, if not send to login page
    if (!this.authenticationService.isLoggedIn()) {
      this.isLoggedIn = false;
    }
  }

  hideAll() {
    this.showBudgets = false;
    this.showDashboard = false;
    this.showTransactions = false;
    this.showAccount = false;
    this.showAPI = false;
  }

  logout() {
    this.authenticationService.logout();
  }
}
