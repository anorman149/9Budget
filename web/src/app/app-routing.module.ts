import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {BudgetComponent} from './component/budget/budget.component';
import {AccountComponent} from './component/account/account.component';
import {AuthGuard} from './security/auth-guard';
import {HomeComponent} from './component/home/home.component';
import {LoginComponent} from './component/login/login.component';
import {SwaggerComponent} from './component/swagger/swagger.component';
import {BudgetNewComponent} from './component/budget-new/budget-new.component';
import {AppComponent} from './app.component';

const routes: Routes = [
  { path: 'budgets',
    component: BudgetComponent,
    canActivate: [AuthGuard]
  },
  { path: 'budgets/new',
    component: BudgetNewComponent,
    canActivate: [AuthGuard]
  },
  { path: 'api',
    component: SwaggerComponent,
    canActivate: [AuthGuard]
  },
  { path: 'accounts',
    component: AccountComponent,
    canActivate: [AuthGuard]
  },
  { path: '',
    component: AppComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'home',
    component: AppComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'login',
    component: LoginComponent
  },

  // Otherwise redirect to Home
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
