package com.ninebudget.controller;

import com.ninebudget.model.*;
import com.ninebudget.model.dto.ApplicationUserDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

@APIController
public class AuthController implements AuthOperations {
    private static final Logger log = LogManager.getLogger(BudgetController.class);

    @Autowired
    private JWTToken jwtToken;

    @Override
    public ResponseEntity<OAuthToken> login(ApplicationUserDto user) throws ServiceException {
        //TODO For now return a token without checking DB
        OAuthToken authToken = new OAuthToken(user);

        try {
            authToken.setToken(jwtToken.provide(authToken));
        } catch (Exception e) {
            log.error(e); //TODO add more
            throw new ServiceException(HttpStatus.FORBIDDEN.toString(), "FATAL", "Could not Authenticate");
        }

        //Create Cookie and place in Response for others to use
        HttpCookie cookie = ResponseCookie.from("token", authToken.getToken())
                .path("/")
//                .secure(true) //TODO uncomment when SSL is setup
                .build();

        //Place in Spring for others to use
        User principal = new User(authToken.getUser().getAccount().getId().toString(), "", Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(principal, jwtToken, Collections.emptyList()));

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(authToken);
    }
}
