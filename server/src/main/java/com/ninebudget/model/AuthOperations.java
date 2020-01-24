package com.ninebudget.model;

import com.ninebudget.model.dto.UserDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

public interface AuthOperations {
    @RequestMapping(value = "/oauth/token",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    OAuthToken login(@Valid @RequestBody UserDto user) throws ServiceException;
}
