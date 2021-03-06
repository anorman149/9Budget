package com.ninebudget.controller;

import com.ninebudget.model.APIController;
import com.ninebudget.model.ServiceException;
import com.ninebudget.model.TransactionOperations;
import com.ninebudget.model.dto.TransactionDto;
import com.ninebudget.service.TransactionService;
import com.ninebudget.util.ResponseUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@APIController
public class TransactionController implements TransactionOperations {
    private static final Logger log = LogManager.getLogger(TransactionController.class);

    @Autowired
    private TransactionService transactionService;

    @Override
    public ResponseEntity<List<TransactionDto>> getAll(String filter) throws ServiceException {
        log.debug("REST request to get a page of Transactions");

        List<TransactionDto> list = transactionService.findAll();

        return ResponseEntity.ok().body(list);
    }

    @Override
    public ResponseEntity<TransactionDto> get(UUID id) throws ServiceException {
        log.debug("REST request to get Transaction : {}", id);

        Optional<TransactionDto> institutionDto = transactionService.findOne(id);

        return ResponseUtil.wrapOrNotFound(institutionDto);
    }

    @Override
    public ResponseEntity<TransactionDto> update(TransactionDto transactionDto) throws ServiceException {
        log.debug("REST request to update Transaction : {}", transactionDto);

        TransactionDto result = transactionService.save(transactionDto);

        URI uri;
        try{
            uri = new URI(String.valueOf(result.getId()));
        } catch (URISyntaxException e) {
            throw new ServiceException(e);
        }

        return ResponseEntity.created(uri).body(result);
    }

    @Override
    public ResponseEntity<TransactionDto> create(TransactionDto transactionDto) throws ServiceException {
        log.debug("REST request to create Transaction : {}", transactionDto);

        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        transactionDto.setAccountID(UUID.fromString(principal.getUsername()));

        TransactionDto result = transactionService.save(transactionDto);

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
        log.debug("REST request to delete Transaction : {}", id);

        transactionService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
