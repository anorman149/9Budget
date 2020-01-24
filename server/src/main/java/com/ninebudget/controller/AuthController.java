package com.ninebudget.controller;

import com.ninebudget.model.*;
import com.ninebudget.model.dto.UserDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@APIController
public class AuthController implements AuthOperations {
    private static final Logger log = LogManager.getLogger(BudgetController.class);

    @Autowired
    private JWTToken jwtToken;

    @Override
    public OAuthToken login(UserDto user) throws ServiceException {
        //TODO For now return a token without checking DB
        OAuthToken authToken = new OAuthToken(user);

        try {
            authToken.setToken(jwtToken.provide(authToken));
        } catch (Exception e) {
            log.error(e); //TODO add more
            throw new ServiceException(HttpStatus.FORBIDDEN.toString(), "FATAL", "Could not Authenticate");
        }

        return authToken;
    }
}
