import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Budget} from "../model/budget";

@Injectable({
  providedIn: 'root'
})
export class BudgetService {
  private readonly budgetUrl: string;

  constructor(private http: HttpClient) {
    this.budgetUrl = 'http://localhost:8080/9budget/budgets';
  }

  public save(budget: Budget) {
    return this.http.post<Budget>(this.budgetUrl + "/" + budget.id, budget);
  }

  public getAll(budget: Budget) {
    const headers = new HttpHeaders()
      .set("Content-Type", "application/json");

    return this.http.get<Budget[]>(this.budgetUrl + "/" + budget.id, {headers});
  }

  public get(budget: Budget) {
    const headers = new HttpHeaders()
      .set("Content-Type", "application/json");

    return this.http.get<Budget>(this.budgetUrl + "/" + budget.id, {headers});
  }
}
