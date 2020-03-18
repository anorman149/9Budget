import { Injectable } from '@angular/core';
import {PrimaryService} from "../model/primary-service";
import {HttpClient, HttpResponse} from "@angular/common/http";
import {api} from "../../environments/environment";
import {Observable} from "rxjs";
import {createRequestOption} from "../shared/util/request-util";
import {ITransaction, Transaction} from "../model/transaction";

type EntityResponseType = HttpResponse<ITransaction>;
type EntityArrayResponseType = HttpResponse<ITransaction[]>;

@Injectable({
  providedIn: 'root'
})
export class TransactionService implements PrimaryService {

  constructor(private http: HttpClient) {}

  public getAll() {
    return this.http.get<Transaction[]>(api.url + api.transaction.url );
  }

  public get(transaction: Transaction) {
    return this.http.get<Transaction>(api.url + api.transaction.url + '/' + transaction.id);
  }

  public query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITransaction[]>(api.url + api.transaction.url, { params: options, observe: 'response' });
  }

  public create(transaction: Transaction) {
    return this.http.put<Transaction>(api.url + api.transaction.url, transaction);
  }

  public update(transaction: Transaction) {
    return this.http.post<Transaction>(api.url + api.transaction.url, transaction);
  }
}
