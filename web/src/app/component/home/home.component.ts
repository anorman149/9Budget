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

  constructor(private authenticationService: AuthenticationService,
              private messageService: MessageService) {

  }

  ngOnInit() {

  }

  logout() {
    this.authenticationService.logout();
  }
}
