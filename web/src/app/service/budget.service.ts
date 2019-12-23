import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Budget} from "../model/budget";
import {PrimaryService} from "../model/primary-service";
import {api} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class BudgetService implements PrimaryService{

  constructor(private http: HttpClient) {}

  public getAll() {
    return this.http.get<Budget[]>(api.url + api.budget.url );
  }

  public get(budget: Budget) {
    return this.http.get<Budget>(api.url + api.budget.url + "/" + budget.id);
  }

  create(budget: Budget): any {
    return this.http.post<Budget>(api.url + api.budget.url  + "/" + budget.id, budget);
  }

  update(budget: Budget): any {
    return this.http.put<Budget>(api.url + api.budget.url  + "/" + budget.id, budget);
  }
}
