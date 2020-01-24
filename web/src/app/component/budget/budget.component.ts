import {Component, OnInit} from '@angular/core';
import {Budget} from '../../model/budget';
import {BudgetService} from '../../service/budget.service';
import {PrimaryComponent} from '../../model/primary-component';
import {BudgetTiming} from '../../model/budget-timing.model';

@Component({
  selector: 'app-budget',
  templateUrl: './budget.component.html',
  styleUrls: ['./budget.component.scss']
})
export class BudgetComponent implements OnInit, PrimaryComponent {
  budget: Budget;

  constructor(private budgetService: BudgetService) {}

  ngOnInit() {
    const budget: Budget = new Budget();
    budget.name = 'a';
    budget.amount = 12;
    budget.budgetTiming = BudgetTiming.BIWEEKLY;
    budget.useLeftOver = false;
    budget.active = true;

    this.budgetService.get(budget).subscribe(data => {
      this.budget = data;
    });
  }

  create(object: any) {

  }

  get(object: any) {

  }

  update(object: any) {

  }

}
