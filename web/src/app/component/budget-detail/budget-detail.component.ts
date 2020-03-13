import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {IBudget} from '../../model/budget';
import {BudgetService} from '../../service/budget.service';
import {BudgetTiming} from '../../model/budget-timing.model';
import {MessageService} from "primeng/api";

@Component({
  selector: 'app-budget-detail',
  templateUrl: './budget-detail.component.html',
  styleUrls: ['./budget-detail.component.scss']
})
export class BudgetDetailComponent implements OnInit {
  budgetDetailForm: FormGroup;

  @Input()
  budget?: IBudget;

  constructor(private budgetService: BudgetService, private messageService: MessageService) {}

  ngOnInit() {
    // Form
    this.budgetDetailForm = new FormGroup({
      budgetTiming: new FormControl(this.budget.budgetTiming, Validators.required),
      amount: new FormControl(this.budget.amount, Validators.required)
    });
  }

  submit() {
    this.budget.budgetTiming = this.budgetDetailForm.get('budgetTiming').value;
    this.budget.amount = this.budgetDetailForm.get('amount').value;
    this.budgetService.update(this.budget).subscribe(
      data => {
        console.log('Budget Updated');
        this.messageService.add({ summary: 'Budget Updated', severity: 'success', sticky: false })
      },
      error => {
        console.log('Budget Failed to update: ' + error);
        this.messageService.add({ summary: 'Budget Failed to update: ' + error, severity: 'error', sticky: false })
      }
    );
  }
}
