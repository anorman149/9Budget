package com.ninebudget.controller;

import com.ninebudget.model.*;
import com.ninebudget.model.dto.ApplicationUserDto;
import com.ninebudget.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Optional;

@APIController
public class AuthController implements AuthOperations {
    private static final Logger log = LogManager.getLogger(BudgetController.class);

    private static final int lockAttempts = 3;
    private static final long lockedOutMins = 30;

    @Autowired
    private JWTToken jwtToken;

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<Object> login(ApplicationUserDto user) throws ServiceException {
        OAuthToken authToken;
        Optional<ApplicationUserDto> dbUser = Optional.empty();
        try {
            //Grab User from DB
            dbUser = userService.findOneByCredential(user.getCredential());

            //Check Password is correct
            userService.checkPassword(user.getCredential().getPassword(), dbUser.get().getCredential().getPassword());

            /*
                Check if locked out, if so, don't authorize
                and increase lock out attempt
             */
            if(checkIfLocked(dbUser.get())){
                throw new InvalidUsernameOrPasswordException();
            }

            authToken = new OAuthToken(dbUser.get());

            authToken.setToken(jwtToken.provide(authToken));
        } catch(InvalidUsernameOrPasswordException ie){
            /*
               Username or password has been used...
               Need to log and save attempt

               Check if User was found in Database first, basically means
               password was wrong and not Username
             */
            HttpStatus status = HttpStatus.FORBIDDEN;
            if(dbUser.isPresent()){
                int attempt = dbUser.get().getFailedLoginAttempts();
                int currentAttempt = attempt +1;
                dbUser.get().setFailedLoginAttempts(currentAttempt);
                dbUser.get().setLastFailedLogin(Instant.now());

                //If the User has attempted more than configured times, lock it
                if(currentAttempt >= lockAttempts){
                    dbUser.get().setLocked(true);
                    /*
                        Resetting the timer on the locked out time

                        Each time the user tries in the time, it will reset it
                     */
                    dbUser.get().setLockedOutUntil(Instant.now().plus(lockedOutMins, ChronoUnit.MINUTES));

                    //Change status to locked
                    status = HttpStatus.LOCKED;
                }

                //Save updates to DB
                userService.save(dbUser.get());

                log.error("User: " + dbUser.get().getCredential().getUsername() + " failed to login...Attempt: " + currentAttempt);
            }

            return ResponseEntity.status(status).body(new ServiceException(status, "FATAL", "Could not Authenticate: " + ie.getMessage()));
        } catch (Exception e) {
            log.error("Error while logging in", e);
            throw new ServiceException(HttpStatus.FORBIDDEN, "FATAL", "Could not Authenticate: " + e.getMessage());
        }

        //Create Cookie and place in Response for others to use
        HttpCookie cookie = ResponseCookie.from("token", authToken.getToken())
                .path("/")
                .maxAge(Duration.ofSeconds(Token.AUTH_TOKEN_EXPIRE_TIME))
//                .secure(true) //TODO uncomment when SSL is setup
                .build();

        //Place in Spring for others to use
        User principal = new User(dbUser.get().getAccount().getId().toString(), "", Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(principal, jwtToken, Collections.emptyList()));

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(authToken);
    }

    public boolean checkIfLocked(ApplicationUserDto applicationUser){
        /*
            Check to see if locked is true, if so
            then check to see if we have passed the locked out
            time, if so, they are not locked anymore; update database

            if not, they are locked
         */
        if(applicationUser.isLocked()){
            //Means we have passed the locked out time
            if(Instant.now().isAfter(applicationUser.getLockedOutUntil())){
                //Update User so they are not locked anymore
                applicationUser.setFailedLoginAttempts(0);
                applicationUser.setLastFailedLogin(null);
                applicationUser.setLocked(false);
                applicationUser.setLockedOutUntil(null);

                //Save updates to DB
                userService.save(applicationUser);

                return false;
            }

            return true;
        }

        return false;
    }
}
