import { Component, OnInit } from '@angular/core';
import {Account} from '../../model/account';
import {AccountService} from '../../service/account.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.scss']
})
export class AccountComponent implements OnInit {
  account: Account;

  constructor(private accountService: AccountService) {}

  ngOnInit() {
    const account: Account = new Account();
    account.id = 2;

    this.accountService.get(account).subscribe(data => {
      this.account = data;
    });
  }
}
