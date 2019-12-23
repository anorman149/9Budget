import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {BudgetComponent} from "./component/budget/budget.component";
import {AccountComponent} from "./component/account/account.component";
import {AuthGuard} from "./security/auth-guard";
import {HomeComponent} from "./component/home/home.component";
import {LoginComponent} from "./component/login/login.component";

const routes: Routes = [
  { path: 'budgets',
    component: BudgetComponent,
    canActivate: [AuthGuard]
  },
  { path: 'accounts',
    component: AccountComponent,
    canActivate: [AuthGuard]
  },
  { path: '',
    component: HomeComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'home',
    component: HomeComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'login',
    component: LoginComponent
  },

  //Otherwise redirect to Home
  {
    path: '**',
    redirectTo: ''
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
