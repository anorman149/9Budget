import {Component} from '@angular/core';
import {AuthenticationService} from './service/authentication.service';
import {MessageService} from 'primeng/api';
import {AutoResume, DEFAULT_INTERRUPTSOURCES, Idle} from '@ng-idle/core';
import {Keepalive} from '@ng-idle/keepalive';
import {api, authentication} from '../environments/environment';
import {Router} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  private display = false;
  private idleState;
  private timedOut = false;
  private lastPing?: Date = null;

  constructor(private authenticationService: AuthenticationService,
              private messageService: MessageService,
              private idle: Idle,
              private keepalive: Keepalive,
              private router: Router) {

    // Sets an idle timeout
    idle.setIdle(authentication.idleTime);
    // Sets a Idle Timeout
    idle.setTimeout(authentication.idleTimeout);
    // Sets the default interrupts, in this case, things like clicks, scrolls, touches to the document
    idle.setInterrupts(DEFAULT_INTERRUPTSOURCES);

    // When the User has TimedOut
    idle.onTimeout.subscribe(() => {
      this.display = false;
      this.idleState = 'Timed out!';
      idle.setAutoResume(AutoResume.notIdle);
      this.timedOut = true;
      console.log(this.idleState);
    });

    // When the User has been Idle - Start
    idle.onIdleStart.subscribe(() => {
      idle.setAutoResume(AutoResume.disabled);
      console.log(this.idleState);
      this.display = true;
    });

    // Warning and countdown for timing out
    idle.onTimeoutWarning.subscribe((countdown) => {
      this.idleState = 'You will time out in ' + countdown + ' seconds!';
    });

    // Sets the ping interval to 15 seconds
    keepalive.interval(15);
    keepalive.onPing.subscribe(() => this.lastPing = new Date());

    this.authenticationService.currentUser$.subscribe(userLoggedIn => {
      if (userLoggedIn) {
        idle.watch();
        this.timedOut = false;
      } else {
        idle.stop();
      }
    });
  }

  message = '';

  reset() {
    this.idle.watch();
    this.timedOut = false;
  }

  stay() {
    this.display = false;
    this.idle.setAutoResume(AutoResume.notIdle);
    this.reset();
  }

  logout() {
    this.display = false;
    this.router.navigate([api.auth.urlLogin]);
  }
}
