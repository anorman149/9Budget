import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {MessageService} from "primeng/api";
import {TransactionService} from "../../service/transaction.service";
import {InstitutionAccountService} from "../../service/institution-account.service";
import {IInstitutionAccount} from "../../model/institution-account";
import {BudgetService} from "../../service/budget.service";
import {IBudget} from "../../model/budget";
import {CategoryType} from "../../model/category-type.model";
import {Category, ICategory} from "../../model/category";
import {ITransaction, Transaction} from "../../model/transaction";

@Component({
  selector: 'app-transaction-new',
  templateUrl: './transaction-new.component.html',
  styleUrls: ['./transaction-new.component.scss']
})
export class TransactionNewComponent implements OnInit {
  transactionNewForm: FormGroup;

  institutionAccounts: IInstitutionAccount[];
  budgets : IBudget[];
  chosenBudget : IBudget;

  keys = Object.keys;
  categoryType = CategoryType;
  category: ICategory;

  transaction: ITransaction;

  constructor(private transactionService: TransactionService,
              private institutionAccountService: InstitutionAccountService,
              private budgetService: BudgetService,
              private messageService: MessageService) {

    this.category = new Category();
    this.transaction = new Transaction();
  }

  ngOnInit() {
    this.institutionAccountService.getAll().subscribe(
      data => {
        this.institutionAccounts = data;
      },
      error => {
        this.messageService.add({ summary: 'Failed to retrieve Institution Accounts: ' + error, severity: 'error', sticky: false })
      }
    );

    this.budgetService.getAll().subscribe(
      data => {
        this.budgets = data;
      },
      error => {
        this.messageService.add({ summary: 'Failed to retrieve Budgets: ' + error, severity: 'error', sticky: false })
      }
    );

    this.transactionNewForm = new FormGroup({
      category: new FormControl('', Validators.required),
      budget: new FormControl('', Validators.required),
      institutionAccount: new FormControl('', Validators.required),
      date: new FormControl(new Date(), Validators.required),
      description: new FormControl('', Validators.required),
      amount: new FormControl(0, Validators.required),
    });
  }

  submit(){
    this.chosenBudget = this.budgets[this.transactionNewForm.get('budget').value];

    this.category.type = CategoryType[this.transactionNewForm.get('category').value] as CategoryType;
    this.transaction.category =  this.category;
    this.transaction.budgetID = this.chosenBudget.id;
    this.transaction.budgetName = this.chosenBudget.name;
    this.transaction.institutionAccount = this.institutionAccounts[this.transactionNewForm.get('institutionAccount').value];
    this.transaction.date = new Date(this.transactionNewForm.get('date').value);
    this.transaction.description = this.transactionNewForm.get('description').value;
    this.transaction.amount = this.transactionNewForm.get('amount').value;

    this.transactionService.create(this.transaction).subscribe(
      data => {
        this.messageService.add({ summary: 'Transaction Created', severity: 'success', sticky: false })
      },
      error => {
        this.messageService.add({ summary: 'Transaction Failed to Create: ' + error, severity: 'error', sticky: false })
      }
    );
  }
}
