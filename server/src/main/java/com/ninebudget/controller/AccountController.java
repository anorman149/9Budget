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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@APIController
public class AccountController implements AccountOperations {
    private static final Logger log = LogManager.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @Override
    public ResponseEntity<List<AccountDto>> getAll(Pageable pageable, @RequestParam(required = false) String filter) throws ServiceException {
        if ("category-is-null".equals(filter)) {
            log.debug("REST request to get all SystemAccounts where category is null");
            return new ResponseEntity<>(accountService.findAllWhereCategoryIsNull(),
                    HttpStatus.OK);
        }

        if ("budget-is-null".equals(filter)) {
            log.debug("REST request to get all SystemAccounts where budget is null");
            return new ResponseEntity<>(accountService.findAllWhereBudgetIsNull(),
                    HttpStatus.OK);
        }

        if ("institution-is-null".equals(filter)) {
            log.debug("REST request to get all SystemAccounts where institution is null");
            return new ResponseEntity<>(accountService.findAllWhereInstitutionIsNull(),
                    HttpStatus.OK);
        }

        log.debug("REST request to get a page of SystemAccounts");

        Page<AccountDto> page = accountService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

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
