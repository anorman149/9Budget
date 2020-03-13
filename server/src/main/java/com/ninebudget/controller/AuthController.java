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
import java.util.Collections;
import java.util.Optional;

@APIController
public class AuthController implements AuthOperations {
    private static final Logger log = LogManager.getLogger(BudgetController.class);

    @Autowired
    private JWTToken jwtToken;

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<OAuthToken> login(ApplicationUserDto user) throws ServiceException {
        OAuthToken authToken;
        Optional<ApplicationUserDto> dbUser;
        try {
            //Grab User from DB
            dbUser = userService.findOneByCredential(user.getCredential());

            //Check Password is correct
            userService.checkPassword(user.getCredential().getPassword(), dbUser.get().getCredential().getPassword());

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
}
