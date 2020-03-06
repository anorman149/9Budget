package com.ninebudget.controller;

import com.ninebudget.model.*;
import com.ninebudget.model.dto.ApplicationUserDto;
import com.ninebudget.service.CredentialService;
import com.ninebudget.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.UUID;

@APIController
public class UserController implements UserOperations {
    private static final Logger log = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private CredentialService credentialService;

//    @Autowired
//    private MailService mailService;

    @Override
    public ResponseEntity<ApplicationUserDto> create(@Valid ApplicationUserDto applicationUser) throws ServiceException {
        log.debug("REST request to create Application User : {}", applicationUser);

        /*
            Check to see if a User exists with the email
            if so, throw error
         */
        if(userService.findOneByEmailIgnoreCase(applicationUser.getEmail()).isPresent()){
            throw new EmailAlreadyUsedException();
        }

        /*
            Check to see if a User exists with the username
            if so, throw error
         */
        if(credentialService.findOneByUsername(applicationUser.getCredential().getUsername()).isPresent()){
            throw new UsernameAlreadyUsedException();
        }

        /*
            Check against Application User ID, as it shouldn't have one
         */
        if(applicationUser.getId() != null){
            throw new ServiceException(HttpStatus.BAD_REQUEST.toString(), "FATAL", "User cannot have an ID already");
        }

        //Create User
        ApplicationUserDto result = userService.createUser(applicationUser);

        //Send Creation Email
//        mailService.sendCreationEmail(result);
        //TODO
        URI uri;
        try{
            uri = new URI(String.valueOf(result.getId()));
        } catch (URISyntaxException e) {
            throw new ServiceException(e);
        }

        return ResponseEntity.created(uri).body(result);
    }

    @Override
    public ResponseEntity<Void> delete(UUID id) throws ServiceException {
        log.debug("REST request to delete Application User : {}", id);

        userService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> activate(String key) throws ServiceException {
        log.debug("REST request to Activate User : {}", key);

        Optional<ApplicationUser> user = userService.activateRegistration(key);

        if (!user.isPresent()) {
            throw new ServiceException(HttpStatus.NOT_FOUND.toString(), "FATAL", "No User was found for this activation key");
        }

        return ResponseEntity.noContent().build();
    }
}
