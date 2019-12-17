import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {BudgetComponent} from "./component/budget/budget.component";
import {AccountComponent} from "./component/account/account.component";

const routes: Routes = [
  { path: 'budgets', component: BudgetComponent },
  { path: 'accounts', component: AccountComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
