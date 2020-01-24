import {Injectable} from '@angular/core';
import {AbstractControl, AsyncValidator, ValidationErrors} from '@angular/forms';
import {Observable} from 'rxjs';
import {AccountService} from '../service/account.service';

@Injectable({ providedIn: 'root' })
export class UniqueUsernameValidator implements AsyncValidator {
  constructor(private accountService: AccountService) {}
// TODO Maybe remove?
  validate(control: AbstractControl): Promise<ValidationErrors | null> | Observable<ValidationErrors | null> {
    this.accountService.getUsernames().subscribe(
      data => {
        /*
          Loop through each Username and check
          If it matches, return true
         */
        for (const username of data) {
          if (username === control.value) {
            return true;
          }
        }
      },
      error => {
        return null;
      }
    );

    return null;
  }
}
