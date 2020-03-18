import { Component, OnInit } from '@angular/core';
import {ITransaction} from "../../model/transaction";
import {MessageService} from "primeng/api";
import {TransactionService} from "../../service/transaction.service";

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.scss']
})
export class TransactionComponent implements OnInit {
  transactions?: ITransaction[];

  constructor(private transactionService: TransactionService,
              private messageService: MessageService) {}

  ngOnInit() {
    this.transactionService.getAll().subscribe(
      data => {
        this.transactions = data ? data : [];

        this.messageService.add({ summary: 'Transactions Loaded', severity: 'success', sticky: false })
      },
      error => this.messageService.add({ summary: 'Failed to Load Transactions: ' + error, severity: 'error', sticky: false })
    );
  }
}
