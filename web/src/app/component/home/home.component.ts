import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from '../../service/authentication.service';
import {MessageService} from 'primeng/api';

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

  constructor(private authenticationService: AuthenticationService,
              private messageService: MessageService) {

  }

  ngOnInit() {

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
