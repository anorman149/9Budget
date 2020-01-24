import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-budget-new',
  templateUrl: './budget-new.component.html',
  styleUrls: ['./budget-new.component.scss']
})
export class BudgetNewComponent implements OnInit {
  display: true;
  budgetNewForm: FormGroup;

  constructor() { }

  ngOnInit() {
    this.display = true;

    // Form
    this.budgetNewForm = new FormGroup({
    });
  }

  submit() {

  }
}
