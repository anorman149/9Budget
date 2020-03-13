import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {User} from "../../model/user";
import {api} from "../../../environments/environment";
import {UserService} from "../../service/user.service";
import {MessageService} from "primeng";

@Component({
  selector: 'app-forgetpassword',
  templateUrl: './forgetpassword.component.html',
  styleUrls: ['./forgetpassword.component.scss']
})

export class ForgetpasswordComponent implements OnInit {
  forgetpasswordForm: FormGroup;

  constructor(private userService: UserService,
              private messageService: MessageService,
              private router: Router) {
  }

  ngOnInit() {
    //Form
    this.forgetpasswordForm = new FormGroup({
      email: new FormControl('', Validators.required)
    })
  }

  submit() {
    //Verify user
    const user = new User();
    user.email = this.forgetpasswordForm.get('email').value;


   // Link send?
    this.userService.resetPassword(user).subscribe(
      data =>  {
        // Route to Home
        this.router.navigate([api.auth.urlLogin]);
      },
      error =>  {
        this.messageService.add({ summary: 'Failed to reset password: ' + error, severity: 'error', sticky: false })
      }
    );
  }
}

