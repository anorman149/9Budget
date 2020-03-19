import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams, HttpResponse} from '@angular/common/http';
import {Account, IAccount} from '../model/account';
import {PrimaryService} from '../model/primary-service';
import {Observable} from 'rxjs';
import {api} from "../../environments/environment";
import {createRequestOption} from "../shared/util/request-util";

type EntityResponseType = HttpResponse<IAccount>;
type EntityArrayResponseType = HttpResponse<IAccount[]>;

@Injectable({
  providedIn: 'root'
})
export class AccountService implements PrimaryService {
  constructor(private http: HttpClient) {}

  public getAll() {
    return this.http.get<Account[]>(api.url + api.account.url );
  }

  public get(account: Account) {
    return this.http.get<Account>(api.url + api.account.url + '/' + account.id);
  }

  public query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAccount[]>(api.url + api.account.url, { params: options, observe: 'response' });
  }

  public create(account: Account) {
    return this.http.put<Account>(api.url + api.account.url, account);
  }

  public update(account: Account) {
    return this.http.post<Account>(api.url + api.account.url, account);
  }

  public delete(account: Account) {
    let httpParams = new HttpParams().set('id', String(account.id));

    let options = { params: httpParams };

    return this.http.delete<Account>(api.url + api.account.url, options);
  }
}
