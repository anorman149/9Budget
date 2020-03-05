package com.ninebudget.controller;

import com.ninebudget.model.APIController;
import com.ninebudget.model.InstitutionAccountOperations;
import com.ninebudget.model.ServiceException;
import com.ninebudget.model.dto.InstitutionAccountDto;
import com.ninebudget.service.InstitutionAccountService;
import com.ninebudget.util.ResponseUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@APIController
public class InstitutionAccountController implements InstitutionAccountOperations {
    private static final Logger log = LogManager.getLogger(InstitutionController.class);

    @Autowired
    private InstitutionAccountService institutionAccountService;

    @Override
    public ResponseEntity<List<InstitutionAccountDto>> getAll(UUID id) throws ServiceException {
        log.debug("REST request to get a page of InstitutionAccounts");
        //TODO
        List<InstitutionAccountDto> page = institutionAccountService.findAll();

        return ResponseEntity.ok().body(page);
    }

    @Override
    public ResponseEntity<InstitutionAccountDto> get(UUID id) throws ServiceException {
        log.debug("REST request to get InstitutionAccount : {}", id);

        Optional<InstitutionAccountDto> institutionDto = institutionAccountService.findOne(id);

        return ResponseUtil.wrapOrNotFound(institutionDto);
    }

    @Override
    public ResponseEntity<InstitutionAccountDto> update(InstitutionAccountDto institutionAccountDto) throws ServiceException {
        log.debug("REST request to update InstitutionAccount : {}", institutionAccountDto);

        InstitutionAccountDto result = institutionAccountService.save(institutionAccountDto);

        URI uri;
        try{
            uri = new URI(String.valueOf(result.getId()));
        } catch (URISyntaxException e) {
            throw new ServiceException(e);
        }

        return ResponseEntity.created(uri).body(result);
    }

    @Override
    public ResponseEntity<InstitutionAccountDto> create(InstitutionAccountDto institutionAccountDto) throws ServiceException {
        log.debug("REST request to create InstitutionAccount : {}", institutionAccountDto);

        InstitutionAccountDto result = institutionAccountService.save(institutionAccountDto);

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
        log.debug("REST request to delete InstitutionAccount : {}", id);

        institutionAccountService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
