import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BudgetComponent} from './component/budget/budget.component';
import {HttpClientModule, HTTP_INTERCEPTORS} from "@angular/common/http";
import {BudgetService} from "./service/budget.service";
import {AccountComponent} from './component/account/account.component';
import {AccountService} from "./service/account.service";
import {AuthenticationService} from "./service/authentication.service";
import {ErrorInterceptor} from "./interceptor/error-interceptor";
import {HttpHeaderInterceptor} from "./interceptor/http-header-interceptor";
import {MessageService} from 'primeng/api';
import {ToastModule} from 'primeng/toast';
import { NgIdleKeepaliveModule } from '@ng-idle/keepalive';
import {DialogModule} from 'primeng/dialog';
import {ButtonModule} from "primeng";
import { HomeComponent } from './component/home/home.component';
import { LoginComponent } from './component/login/login.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatIconModule} from "@angular/material/icon";
import {MatInputModule} from "@angular/material/input";
import { ClarityModule } from "@clr/angular";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { BudgetDetailComponent } from './component/budget-detail/budget-detail.component';
import { AccountDetailComponent } from './component/account-detail/account-detail.component';
import { SettingsComponent } from './component/settings/settings.component';
import { TransactionComponent } from './component/transaction/transaction.component';
import { InstitutionComponent } from './component/institution/institution.component';
import { InstitutionCreateComponent } from './component/institution-create/institution-create.component';
import { SwaggerComponent } from './component/swagger/swagger.component';

@NgModule({
  declarations: [
    AppComponent,
    BudgetComponent,
    AccountComponent,
    HomeComponent,
    LoginComponent,
    BudgetDetailComponent,
    AccountDetailComponent,
    SettingsComponent,
    TransactionComponent,
    InstitutionComponent,
    InstitutionCreateComponent,
    SwaggerComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    ToastModule,
    NgIdleKeepaliveModule.forRoot(),
    DialogModule,
    ButtonModule,
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
    ClarityModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    BudgetService,
    AccountService,
    AuthenticationService,
    MessageService,
    {provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: HttpHeaderInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
