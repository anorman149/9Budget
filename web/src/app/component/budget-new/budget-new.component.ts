import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Budget, IBudget} from '../../model/budget';
import {BudgetService} from '../../service/budget.service';
import {CategoryType} from '../../model/category-type.model';
import {BudgetTiming} from '../../model/budget-timing.model';
import {Category, ICategory} from "../../model/category";
import {IAccount} from "../../model/account";

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

  account: IAccount;
  budget: IBudget;
  category: ICategory;

  constructor(private budgetService: BudgetService) {
    this.budget = new Budget();
    this.category = new Category();
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

  submit() {
    this.budget.name = this.budgetNewForm.get('name').value;
    this.budget.amount = this.budgetNewForm.get('amount').value;
    this.category.type = CategoryType[this.budgetNewForm.get('category').value] as CategoryType;
    this.budget.category =  this.category;
    this.budget.useLeftOver = this.budgetNewForm.get('useLeftOver').value as boolean;
    this.budget.budgetTiming = this.budgetNewForm.get('budgetTiming').value;
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
