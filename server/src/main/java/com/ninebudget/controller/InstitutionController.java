package com.ninebudget.controller;

import com.ninebudget.model.APIController;
import com.ninebudget.model.InstitutionOperations;
import com.ninebudget.model.ServiceException;
import com.ninebudget.model.dto.InstitutionDto;
import com.ninebudget.service.InstitutionService;
import com.ninebudget.util.ResponseUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@APIController
public class InstitutionController implements InstitutionOperations {
    private static final Logger log = LogManager.getLogger(InstitutionController.class);

    @Autowired
    private InstitutionService institutionService;

    @Override
    public ResponseEntity<List<InstitutionDto>> getAll(Pageable pageable, String filter) throws ServiceException {
        log.debug("REST request to get a page of Institutions");

        Page<InstitutionDto> page = institutionService.findAll(pageable);

        return ResponseEntity.ok().body(page.getContent());
    }

    @Override
    public ResponseEntity<InstitutionDto> get(UUID id) throws ServiceException {
        log.debug("REST request to get Institution : {}", id);

        Optional<InstitutionDto> institutionDto = institutionService.findOne(id);

        return ResponseUtil.wrapOrNotFound(institutionDto);
    }

    @Override
    public ResponseEntity<InstitutionDto> update(InstitutionDto institution) throws ServiceException {
        log.debug("REST request to update Institution : {}", institution);

        InstitutionDto result = institutionService.save(institution);

        URI uri;
        try{
            uri = new URI(String.valueOf(result.getId()));
        } catch (URISyntaxException e) {
            throw new ServiceException(e);
        }

        return ResponseEntity.created(uri).body(result);
    }

    @Override
    public ResponseEntity<InstitutionDto> create(InstitutionDto institution) throws ServiceException {
        log.debug("REST request to create Institution : {}", institution);

        InstitutionDto result = institutionService.save(institution);

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
        log.debug("REST request to delete Institution : {}", id);

        institutionService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
