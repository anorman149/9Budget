import {Component, OnInit} from '@angular/core';
import {Budget} from "../../model/budget";
import {BudgetService} from "../../service/budget.service";
import {PrimaryComponent} from "../../model/primary-component";

@Component({
  selector: 'app-budget',
  templateUrl: './budget.component.html',
  styleUrls: ['./budget.component.scss']
})
export class BudgetComponent implements OnInit, PrimaryComponent {
  budget: Budget;

  constructor(private budgetService: BudgetService) {}

  ngOnInit() {
    let budget: Budget = new Budget();
    budget.id = 4;

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
