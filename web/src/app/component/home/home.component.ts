import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from '../../service/authentication.service';
import {MessageService} from 'primeng/api';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  private collapsed: boolean = true; //Start collapsed Navigation
  private showBudgets: boolean = false;
  private showDashboard: boolean = true; //Default View
  private showTransactions: boolean = false;
  private showAccount: boolean = false;
  private showSettings: boolean = false;
  private showAPI: boolean = false;

  constructor(private authenticationService: AuthenticationService,
              private messageService: MessageService) {

  }

  ngOnInit() {

  }

  hideAll(){
    this.showBudgets = false;
    this.showDashboard = false;
    this.showTransactions = false;
    this.showAccount = false;
    this.showSettings = false;
    this.showAPI = false;
  }

  logout(){
    this.authenticationService.logout();
  }
}
