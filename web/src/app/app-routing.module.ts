import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {BudgetComponent} from './component/budget/budget.component';
import {AccountComponent} from './component/account/account.component';
import {AuthGuard} from './security/auth-guard';
import {LoginComponent} from './component/login/login.component';
import {SwaggerComponent} from './component/swagger/swagger.component';
import {BudgetNewComponent} from './component/budget-new/budget-new.component';
import {InstitutionComponent} from "./component/institution/institution.component";
import {ForgetpasswordComponent} from "./component/forgetpassword/forgetpassword.component";
import {HomeComponent} from "./component/home/home.component";
import {ResetpasswordComponent} from "./component/resetpassword/resetpassword.component";


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
  { path: 'institution',
    component: InstitutionComponent,
    canActivate: [AuthGuard]
  },
  { path: 'forgetpassword',
    component: ForgetpasswordComponent
    // no AuthGuard
  },

  //Addding new routing for forget password page
  { path: 'resetpassword/:id',
    component: ResetpasswordComponent
    // no AuthGuard
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
