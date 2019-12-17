import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BudgetComponent } from './component/budget/budget.component';
import {HttpClientModule} from "@angular/common/http";
import {BudgetService} from "./service/budget.service";
import { AccountComponent } from './component/account/account.component';
import {AccountService} from "./service/account.service";

@NgModule({
  declarations: [
    AppComponent,
    BudgetComponent,
    AccountComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [BudgetService, AccountService],
  bootstrap: [AppComponent]
})
export class AppModule { }
