import { Component, OnInit } from '@angular/core';
import {Account} from "../../model/account";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AccountService} from "../../service/account.service";
import {UserService} from "../../service/user.service";
import {Router} from '@angular/router';
import {User} from "../../model/user";
import {Credential} from "../../model/credential";
import {MessageService} from "primeng/api";


@Component({
  selector: 'app-account-register',
  templateUrl: './account-register.component.html',
  styleUrls: ['./account-register.component.scss']
})
export class AccountRegisterComponent implements OnInit {

  account: Account;
  SignUpForm: FormGroup;
  private errorActive = '';
  private errorMessage: string;


  constructor(private userService: UserService,private accountService: AccountService,private messageService: MessageService ,private router: Router) {
  }


  ngOnInit() {
    // Form
    this.SignUpForm = new FormGroup({
      username: new FormControl('', Validators.required),
      password: new FormControl('', [Validators.required, Validators.minLength(8)]),
      firstname: new FormControl('', Validators.required),
      lastname: new FormControl('', Validators.required),
      email: new FormControl('', [Validators.required, Validators.pattern("[a-zA-Z0-9!#$%&'*+\/=?^_`{|}~.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*")]),
      phone: new FormControl('', [Validators.required, Validators.maxLength(12),Validators.pattern("[0-9]{3}-[0-9]{3}-[0-9]{4}")]),


    });
  }

  submit() {
    // Setup Account
    const account = new Account();
    const user=new User();
    const credential = new Credential();


    user.firstName = this.SignUpForm.get('firstname').value;
    user.lastName = this.SignUpForm.get('lastname').value;
    credential.username = this.SignUpForm.get('username').value;
    credential.password = this.SignUpForm.get('password').value;
    user.email = this.SignUpForm.get('email').value;
    user.phone = this.SignUpForm.get('phone').value;


    user.credential = credential;
    account.name=credential.username;

    // Create an Account
    //This will give you back the account ID
    //Then you can use that accountid to call the user create service
    this.accountService.create(account).subscribe(
      data => {
        user.accountId=data.id;
       console.log("Account created");

      },
      error => {
        this.messageService.add({ summary: 'Failed to Create Account: ' + error, severity: 'error', sticky: false })
      }
    );
    
    this.userService.create(user).subscribe(
      data =>  {
        // Route to Home
        console.log("user created");
        this.messageService.add({ summary: 'User Registered', severity: 'success', sticky: false })
        this.router.navigate(['/login']);

      },
      error =>  {
        this.messageService.add({ summary: 'Failed to Create User: ' + error, severity: 'error', sticky: false })
      }
    );



  }
}
