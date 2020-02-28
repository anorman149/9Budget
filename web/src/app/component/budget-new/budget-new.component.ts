import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {IBudget} from '../../model/budget';
import {BudgetService} from '../../service/budget.service';
import {CategoryType} from '../../model/category-type.model';
import {BudgetTiming} from '../../model/budget-timing.model';

@Component({
  selector: 'app-budget-new',
  templateUrl: './budget-new.component.html',
  styleUrls: ['./budget-new.component.scss']
})
export class BudgetNewComponent implements OnInit {
  display: true;
  budgetNewForm: FormGroup;

  keys = Object.keys;
  categoryType = CategoryType;

  budget: IBudget;

  constructor(private budgetService: BudgetService) {
  }

  ngOnInit() {
    this.display = true;

    this.budgetNewForm = new FormGroup({
      category: new FormControl('', Validators.required),
      name: new FormControl('', Validators.required),
      amount: new FormControl(0, Validators.required), // Default to 0
      budgetTiming: new FormControl(BudgetTiming.MONTHLY, Validators.required), // Default to Monthly
      useLeftOver: new FormControl(false, Validators.required) // Default to False
    });
  }

  public submit(): void {
    this.budget.category = this.budgetNewForm.get('category').value;
    this.budget.name = this.budgetNewForm.get('name').value;
    this.budget.amount = this.budgetNewForm.get('amount').value;
    this.budget.budgetTiming = this.budgetNewForm.get('budgetTiming').value;
    this.budget.useLeftOver = this.budgetNewForm.get('use_leftover').value;
    this.budgetService.create(this.budget).subscribe(
      data => {
        console.log('Budget Created');
      },
      error => {
        console.log('Budget Failed to Create: ' + error);
        alert('Budget Failed to Create: ' + error);
      }
    );
  }
}
