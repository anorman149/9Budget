import { Component, OnInit } from '@angular/core';
import {Account} from '../../model/account';
import {AccountService} from '../../service/account.service';
import {PrimaryComponent} from '../../model/primary-component';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.scss']
})
export class AccountComponent implements OnInit, PrimaryComponent {
  account: Account;

  constructor(private accountService: AccountService) {}

  ngOnInit() {
    const account: Account = new Account();
    account.id = 2;

    this.accountService.get(account).subscribe(data => {
      this.account = data;
    });
  }

  create(object: any): any {
  }

  get(object: any): any {
  }

  update(object: any): any {
  }
}
