package com.ninebudget.model;

import com.ninebudget.model.dto.InstitutionDto;
import com.ninebudget.validator.uuid.ValidUUID;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Validated
public interface InstitutionOperations {
    @RequestMapping(value = "/institutions",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<InstitutionDto>> getAll(@RequestParam(required = false) String filter) throws ServiceException;

    @RequestMapping(value = "/institutions/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<InstitutionDto> get(@ValidUUID @PathVariable UUID id) throws ServiceException;

    @RequestMapping(value = "/institutions",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<InstitutionDto> update(@Valid @RequestBody InstitutionDto institution) throws ServiceException;

    @RequestMapping(value = "/institutions",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<InstitutionDto> create(@Valid @RequestBody InstitutionDto institution) throws ServiceException;

    @RequestMapping(value = "/institutions/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> delete(@ValidUUID @PathVariable UUID id) throws ServiceException;
}
