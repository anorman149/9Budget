import {Component, OnInit} from '@angular/core';
import {IBudget} from '../../model/budget';
import {BudgetService} from '../../service/budget.service';
import {CategoryType} from '../../model/category-type.model';

@Component({
  selector: 'app-budget',
  templateUrl: './budget.component.html',
  styleUrls: ['./budget.component.scss']
})
export class BudgetComponent implements OnInit {
  budgets?: IBudget[];
  leftOverColor = '';
  leftOverAmount = 0;
  spent = 0;
  income = 0;
  budgetNewDisplay = false;
  budgetDetailDisplay = false;

  constructor(private budgetService: BudgetService) {}

  ngOnInit() {
    this.budgetService.getAll().subscribe(
      data => {
        this.budgets = data ? data : [];

        // Loop through Each Budget and set appropriate Values
        for (const budget of this.budgets) {
          budget.amountSpent = 0;

          // Calculate Necessary items with Transactions
          for (const trans of budget.transactions) {
            budget.amountSpent += trans.amount; // Set Amount Spent for Budget

            // Gather Spent vs Income
            if (trans.category.type === CategoryType.INCOME) {
              this.income += trans.amount; // Add to total Income
            } else {
              this.spent += trans.amount; // Add to total spent
            }
          }

          // Calculate Money left Over
          this.leftOverAmount = this.income - this.spent;

          // Assign CSS Value for leftOver
          this.leftOverColor = 'value-up'; // Start Positive
          if (this.leftOverAmount < 0) {
            this.leftOverColor = 'value-down';
          }
        }
      },
      error => console.log('ERROR') // TODO
    );
  }

  checkProgressType(budget: IBudget) {
    // Set Progress Bar Type
    let progressType = '';  // Set for Goal Met
    if (budget.amountSpent > budget.amount) {
      // Set for Failure
      progressType = 'danger';
    } else if (budget.amountSpent < budget.amount) {
      // Set for Goal Not Yet Met
      progressType = 'success';
    }

    return progressType;
  }
}
