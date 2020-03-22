import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {User} from "../../model/user";
import {Credential} from "../../model/credential";
import {UserService} from "../../service/user.service";
import {MessageService} from "primeng/api";

@Component({
  selector: 'app-adduser',
  templateUrl: './adduser.component.html',
  styleUrls: ['./adduser.component.scss']
})
export class AdduserComponent implements OnInit {
  addUserForm: FormGroup;

  constructor(private userService: UserService
    , private messageService: MessageService) {

  }

  submit() {

    // Setup User
    const user = new User();
    const credential = new Credential();
    credential.username = this.addUserForm.get('username').value;
    credential.password = this.addUserForm.get('password').value;
    user.firstName = this.addUserForm.get('firstName').value;
    user.lastName = this.addUserForm.get('lastName').value;
    user.phone = this.addUserForm.get('phone').value;
    user.email = this.addUserForm.get('email').value;

    user.credential = credential;

    // Adding user
    this.userService.create(user).subscribe(
      data => {
        this.messageService.add({summary: 'User added', severity: 'success', sticky: false})
      },
      error => this.messageService.add({summary: 'Failed to add user ' + error, severity: 'error', sticky: false})
    );
  }

  ngOnInit() {
    // Form
    this.addUserForm = new FormGroup({
      username: new FormControl('', Validators.required),
      password: new FormControl('', [Validators.required, Validators.minLength(8)]),
      firstName: new FormControl('', Validators.required),
      lastName: new FormControl('', Validators.required),
      phone: new FormControl('', Validators.required),
      email: new FormControl('', Validators.required),
    });
  }

}
