package com.ninebudget.model;

import com.ninebudget.model.dto.AccountDto;
import com.ninebudget.validator.uuid.ValidUUID;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.UUID;

@Validated
public interface AccountOperations {
    @RequestMapping(value = "/accounts/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AccountDto> get(@ValidUUID @PathVariable UUID id) throws ServiceException;

    @RequestMapping(value = "/accounts",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AccountDto> update(@Valid @RequestBody AccountDto account) throws ServiceException;

    @RequestMapping(value = "/accounts",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AccountDto> create(@Valid @RequestBody AccountDto account) throws ServiceException;

    @RequestMapping(value = "/accounts/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> delete(@ValidUUID @PathVariable UUID id) throws ServiceException;
}
