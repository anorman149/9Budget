import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {IBudget} from '../../model/budget';
import {BudgetService} from '../../service/budget.service';
import {CategoryType} from "../../model/category-type.model";

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

  @Input()
  budget?: IBudget;

  constructor(private budgetService: BudgetService) {
  }

  ngOnInit() {
    this.display = true;

    this.budgetNewForm = new FormGroup({
      category: new FormControl(this.budget.category, Validators.required),
      name: new FormControl(this.budget.name, Validators.required),
      amount: new FormControl(this.budget.amount, Validators.required),
      budgetTiming: new FormControl(this.budget.budgetTiming, Validators.required),
      useLeftOver: new FormControl(this.budget.useLeftOver, Validators.required)
    });
  }

  public submit(): void {
    this.budget.category = this.budgetNewForm.get('category').value;
    this.budget.name = this.budgetNewForm.get('name').value;
    this.budget.amount = this.budgetNewForm.get('amount').value;
    this.budget.budgetTiming = this.budgetNewForm.get('budgetTiming').value;
    this.budget.useLeftOver = this.budgetNewForm.get('use_leftover').value;
    this.budgetService.create(this.budget).subscribe(
      data => {},
      error => console.log("ERROR")
    );
  }
}
