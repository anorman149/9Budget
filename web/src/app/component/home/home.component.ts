import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "../../service/authentication.service";
import {MessageService} from "primeng/api";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  private collapsed: boolean = true; //Start collapsed Navigation
  private showBudgets: boolean = false;
  private showDashboard: boolean = false;
  private showTransactions: boolean = true; //Default View
  private showAccount: boolean = false;
  private showSettings: boolean = false;

  constructor(private authenticationService: AuthenticationService,
              private messageService: MessageService) {

  }

  ngOnInit() {

  }

  logout(){
    this.authenticationService.logout();
  }
}
