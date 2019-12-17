import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Account} from "../model/account";

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  private readonly accountUrl: string;

  constructor(private http: HttpClient) {
    this.accountUrl = 'http://localhost:8080/9budget/accounts';
  }

  public save(account: Account) {
    return this.http.post<Account>(this.accountUrl + "/" + account.id, account);
  }

  public getAll(account: Account) {
    const headers = new HttpHeaders()
      .set("Content-Type", "application/json");

    return this.http.get<Account[]>(this.accountUrl + "/" + account.id, {headers});
  }

  public get(account: Account) {
    const headers = new HttpHeaders()
      .set("Content-Type", "application/json");

    return this.http.get<Account>(this.accountUrl + "/" + account.id, {headers});
  }
}
