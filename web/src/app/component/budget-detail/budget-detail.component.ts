import {Component, Input, OnInit} from '@angular/core';
import {FormGroup} from '@angular/forms';
import {IBudget} from '../../model/budget';

@Component({
  selector: 'app-budget-detail',
  templateUrl: './budget-detail.component.html',
  styleUrls: ['./budget-detail.component.scss']
})
export class BudgetDetailComponent implements OnInit {
  display: true;
  budgetDetailForm: FormGroup;

  @Input()
  budget?: IBudget;
  budgetTiming: any;

  constructor() {}

  ngOnInit() {
    this.display = true;

    // Form
    this.budgetDetailForm = new FormGroup({
    });
  }

  submit() {

  }
}
