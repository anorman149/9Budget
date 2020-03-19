package com.ninebudget.controller;

import com.ninebudget.model.*;
import com.ninebudget.model.dto.ApplicationUserDto;
import com.ninebudget.service.CredentialService;
import com.ninebudget.service.MailService;
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

    @Autowired
    private MailService mailService;

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
            throw new ServiceException(HttpStatus.BAD_REQUEST, "FATAL", "User cannot have an ID already");
        }

        //Create User
        ApplicationUserDto user = userService.createUser(applicationUser);

        URI uri;
        try{
            //Send Creation Email
            mailService.sendActivationEmail(user);

            uri = new URI(String.valueOf(user.getId()));
        } catch (URISyntaxException | MailException e) {
            //Remove User since there was an error
            userService.delete(user.getId());

            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, "FATAL", "Could not send email for User to Activate");
        }

        return ResponseEntity.created(uri).body(user);
    }

    @Override
    public ResponseEntity<Void> delete(UUID id) throws ServiceException {
        log.debug("REST request to delete Application User : {}", id);

        userService.delete(id);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> activate(String key) throws ServiceException {
        log.debug("REST request to Activate User : {}", key);

        Optional<ApplicationUser> user = userService.activateRegistration(key);

        if (!user.isPresent()) {
            throw new ServiceException(HttpStatus.NOT_FOUND, "FATAL", "No User was found for this activation key");
        }

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> requestPasswordReset(@Valid ApplicationUserDto applicationUser) throws ServiceException {
        log.debug("REST request to Reset Password for: : {}", applicationUser.getEmail());

        Optional<ApplicationUserDto> user = userService.requestPasswordReset(applicationUser.getEmail());

        if (!user.isPresent()) {
            throw new ServiceException(HttpStatus.NOT_FOUND, "FATAL", "Could not Allow User to reset password");
        }

        try{
            //Send Password Reset Email
            mailService.sendPasswordResetMail(user.get());
        } catch (MailException e) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, "FATAL", "Could not send email to Reset Password");
        }

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> completePasswordReset(@Valid ApplicationUserDto applicationUser) throws ServiceException {
        log.debug("REST request to Complete Password Reset for: : {}", applicationUser.getEmail());

        Optional<ApplicationUserDto> user = userService.completePasswordReset(applicationUser.getCredential().getPassword(), applicationUser.getResetKey());

        if (!user.isPresent()) {
            throw new ServiceException(HttpStatus.FORBIDDEN, "FATAL", "Could not Allow User to reset password");
        }

        try{
            //Send Password Reset Email
            mailService.sendCompleteResetMail(user.get());
        } catch (MailException e) {
            log.warn("Could not send Password Rest Email to User: ", e);
        }

        return ResponseEntity.ok().build();
    }

}
