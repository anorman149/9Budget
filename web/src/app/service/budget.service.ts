import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams, HttpResponse} from '@angular/common/http';
import {Budget, IBudget} from '../model/budget';
import {PrimaryService} from '../model/primary-service';
import {api} from '../../environments/environment';
import {Observable} from 'rxjs';
import {createRequestOption} from '../shared/util/request-util';

type EntityResponseType = HttpResponse<IBudget>;
type EntityArrayResponseType = HttpResponse<IBudget[]>;

@Injectable({
  providedIn: 'root'
})
export class BudgetService implements PrimaryService {

  constructor(private http: HttpClient) {}

  public getAll() {
    return this.http.get<Budget[]>(api.url + api.budget.url );
  }

  public get(budget: Budget) {
    return this.http.get<Budget>(api.url + api.budget.url + '/' + budget.id);
  }

  public query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBudget[]>(api.url + api.budget.url, { params: options, observe: 'response' });
  }

  public create(budget: Budget) {
    return this.http.put<Budget>(api.url + api.budget.url, budget);
  }

  public update(budget: Budget) {
    return this.http.post<Budget>(api.url + api.budget.url, budget);
  }

  public delete(budget: Budget) {
    let httpParams = new HttpParams().set('id', String(budget.id));

    let options = { params: httpParams };

    return this.http.delete<Budget>(api.url + api.budget.url, options);
  }
}
