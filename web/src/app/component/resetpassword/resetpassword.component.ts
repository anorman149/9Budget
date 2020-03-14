import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../service/user.service";
import {MessageService} from "primeng";
import {ActivatedRoute, Router} from "@angular/router";
import {User} from "../../model/user";
import {api} from "../../../environments/environment";
import {Credential} from "../../model/credential";

@Component({
  selector: 'app-resetpassword',
  templateUrl: './resetpassword.component.html',
  styleUrls: ['./resetpassword.component.scss']
})
export class ResetpasswordComponent implements OnInit {
  resetpasswordForm: FormGroup;

  constructor(private userService: UserService,
              private messageService: MessageService,
              private router: Router,
              private activatedRoute: ActivatedRoute) {
  }

  ngOnInit() {
    //Form
    this.resetpasswordForm = new FormGroup({
      password: new FormControl('', [Validators.required, Validators.minLength(8)])
    })
  }

  submit() {
    //Verify user
    const user = new User();
    const credential = new Credential();
    credential.password = this.resetpasswordForm.get('password').value;
    user.credential = credential;
    user.resetKey = this.activatedRoute.snapshot.params.id;

    // Link send?
    this.userService.completePasswordRest(user).subscribe(
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
