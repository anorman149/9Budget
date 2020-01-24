import { Component, OnInit } from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';
import {MessageService} from 'primeng/api';
import {AuthenticationService} from '../../service/authentication.service';
import {Router} from '@angular/router';
import {User} from '../../model/user';
import {Credential} from '../../model/credential';
import {api} from '../../../environments/environment';

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
              private router: Router) {


  }

  ngOnInit() {
    // Form
    this.loginForm = new FormGroup({
      username: new FormControl('', Validators.required),
      password: new FormControl('', [Validators.required, Validators.minLength(8)])
    });

    /*
      Check if account is Locked, if so, throw toast
      and don't allow login
     */
    // TODO
  }

  submit() {
    // Setup User
    const user = new User();
    user.login = this.loginForm.get('username').value;
    user.password = this.loginForm.get('password').value;

    // Login
    this.authenticationService.login(user).subscribe(
      data =>  {
        // Route to Home
        this.router.navigate([api.home.url]);
      },
      error =>  {
        /*
          If error, throw Toast and do not route

          If login Attempts are greater than 3,
          lock account and reset password
         */
        // TODO
        this.errorActive = 'active';
        this.errorMessage = 'Username or Password is incorrect';
      }
    );
  }

  public get username() { return this.loginForm.get('username'); }
}
