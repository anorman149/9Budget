package com.ninebudget.controller;

import com.ninebudget.model.*;
import com.ninebudget.model.dto.ApplicationUserDto;
import com.ninebudget.model.dto.CredentialDto;
import com.ninebudget.service.CredentialService;
import com.ninebudget.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

@APIController
public class AuthController implements AuthOperations {
    private static final Logger log = LogManager.getLogger(BudgetController.class);

    @Autowired
    private JWTToken jwtToken;

    @Autowired
    private UserService userService;

    @Autowired
    private CredentialService credentialService;

    @Override
    public ResponseEntity<OAuthToken> login(ApplicationUserDto user) throws ServiceException {
        OAuthToken authToken;
        Optional<ApplicationUserDto> dbUser;
        try {
            dbUser = userService.findOneByCredential(user.getCredential());

            if (isUserLockedOut(dbUser.get().getCredential())) {
                throw new ServiceException(HttpStatus.FORBIDDEN.toString(), "FATAL", "Account locked");
            }

            boolean isPasswordValid = checkIfPasswordIsCorrect(user, dbUser);

            if (isPasswordValid) {
                resetFailedLoginsAndContinue(dbUser.get().getCredential());
            } else {
                saveFailedLoginAttempt(dbUser);
            }

            authToken = new OAuthToken(dbUser.get());

            authToken.setToken(jwtToken.provide(authToken));
        } catch (Exception e) {
            log.error("Error while logging in", e);
            throw new ServiceException(HttpStatus.FORBIDDEN.toString(), "FATAL", "Could not Authenticate: " + e.getMessage());
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

    private boolean isUserLockedOut(CredentialDto userCredentials) {
        return userMetFailureThreshold(userCredentials) && LocalDateTime.now().isBefore(userCredentials.getLockedOutUntil());
    }

    private boolean checkIfPasswordIsCorrect(ApplicationUserDto user, Optional<ApplicationUserDto> dbUser) throws Exception {
        return userService.checkPassword(user.getCredential().getPassword(), dbUser.get().getCredential().getPassword());
    }

    private void resetFailedLoginsAndContinue(CredentialDto userCredentials) {
        userCredentials.setFailedLoginCount(0);
        credentialService.save(userCredentials);
    }

    private void saveFailedLoginAttempt(Optional<ApplicationUserDto> dbUser) throws ServiceException {
        CredentialDto userCredentials = dbUser.get().getCredential();
        if (userMetFailureThreshold(userCredentials)) {
            resetFailedLoginCount(userCredentials);
            lockAccountTemporarily(userCredentials);
        } else {
            incrementFailedLoginCount(userCredentials);
            lockAccountTemporarily(userCredentials);
        }
        throw new ServiceException(HttpStatus.FORBIDDEN.toString(), "FATAL", "Failed login");
    }

    private boolean userMetFailureThreshold(CredentialDto userCredentials) {
        return userCredentials.getFailedLoginCount() == 3;
    }

    private void resetFailedLoginCount(CredentialDto userCredentials) {
        userCredentials.setFailedLoginCount(1);
    }

    private void incrementFailedLoginCount(CredentialDto userCredentials) {
        userCredentials.setFailedLoginCount(userCredentials.getFailedLoginCount() + 1);
    }

    private void lockAccountTemporarily(CredentialDto userCredentials) {
        userCredentials.setLastFailedLogin(LocalDateTime.now());
        userCredentials.setLockedOutUntil(LocalDateTime.now().plusMinutes(30));
        credentialService.save(userCredentials);
    }
}
