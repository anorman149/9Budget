package com.ninebudget.controller;

import com.ninebudget.model.APIController;
import com.ninebudget.model.AccountOperations;
import com.ninebudget.model.ServiceException;
import com.ninebudget.model.dto.AccountDto;
import com.ninebudget.service.AccountService;
import com.ninebudget.util.ResponseUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.UUID;

@APIController
public class AccountController implements AccountOperations {
    private static final Logger log = LogManager.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @Override
    public ResponseEntity<AccountDto> get(UUID id) throws ServiceException {
        log.debug("REST request to get SystemAccount : {}", id);

        Optional<AccountDto> systemAccountDto = accountService.findOne(id);

        return ResponseUtil.wrapOrNotFound(systemAccountDto);
    }

    @Override
    public ResponseEntity<AccountDto> update(@Valid AccountDto accountDto) throws ServiceException {
        log.debug("REST request to update SystemAccount : {}", accountDto);

        AccountDto result = accountService.save(accountDto);

        URI uri;
        try{
            uri = new URI(String.valueOf(result.getId()));
        } catch (URISyntaxException e) {
            throw new ServiceException(e);
        }

        return ResponseEntity.created(uri).body(result);
    }

    @Override
    public ResponseEntity<AccountDto> create(@Valid AccountDto accountDto) throws ServiceException {
        log.debug("REST request to create SystemAccount : {}", accountDto);

        AccountDto result = accountService.save(accountDto);

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
        log.debug("REST request to delete SystemAccount : {}", id);

        accountService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
