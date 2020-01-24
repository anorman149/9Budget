import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Account} from '../model/account';
import {PrimaryService} from '../model/primary-service';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AccountService implements PrimaryService {
  private readonly accountUrl: string;

  constructor(private http: HttpClient) {
    this.accountUrl = '/9budget/api/v1/accounts';
  }

  public getAll() {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');

    return this.http.get<Account[]>(this.accountUrl, {headers});
  }

  public get(account: Account) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');

    return this.http.get<Account>(this.accountUrl + '/' + account.id, {headers});
  }

  public create(account: Account) {
    return this.http.post<Account>(this.accountUrl + '/' + account.id, account);
  }

  public update(account: Account) {
    return this.http.put<Account>(this.accountUrl + '/' + account.id, account);
  }

  public getUsernames(): Observable<string[]> { // TODO
    return new Observable<string[]>();
  }
}
