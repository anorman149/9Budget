package com.ninebudget.model;

import com.ninebudget.model.dto.ApplicationUserDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Validated
public interface AuthOperations {
    @RequestMapping(value = "/oauth/token",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OAuthToken> login(@Valid @RequestBody ApplicationUserDto user) throws ServiceException;
}
