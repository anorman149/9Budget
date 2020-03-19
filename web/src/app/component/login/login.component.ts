import { Component, OnInit } from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';
import {MessageService} from 'primeng/api';
import {AuthenticationService} from '../../service/authentication.service';
import {Router} from '@angular/router';
import {User} from '../../model/user';
import {Credential} from '../../model/credential';
import {api} from '../../../environments/environment';
import {BudgetService} from "../../service/budget.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss', ]
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  private errorActive = '';
  private errorMessage: string;

  constructor(private authenticationService: AuthenticationService,
              private router: Router,
              private messageService: MessageService) {


  }

  ngOnInit() {
    // Form
    this.loginForm = new FormGroup({
      username: new FormControl('', Validators.required),
      password: new FormControl('', [Validators.required, Validators.minLength(8)])
    });
  }

  submit() {
    // Setup User
    const user = new User();
    const credential = new Credential();
    credential.username = this.loginForm.get('username').value;
    credential.password = this.loginForm.get('password').value;
    user.credential = credential;

    // Login
    this.authenticationService.login(user).subscribe(
      data =>  {
        // Route to Home
        this.router.navigate([api.home.url]);
      },
      error =>  {
        this.errorMessage = 'Username or Password is incorrect';
        this.errorActive = 'active';

        if(error == 'Locked'){
          //Means the account is locked
          this.errorMessage += ': Account Locked; try again in 30 minutes';
        }
      }
    );
  }

  public get username() { return this.loginForm.get('username'); }
}
