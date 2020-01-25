package com.ninebudget.model;

import com.ninebudget.model.dto.InstitutionAccountDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface InstitutionAccountOperations {
    @RequestMapping(value = "/accounts/{id}/institution-accounts",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<InstitutionAccountDto>> getAll(Pageable pageable, @PathVariable Long id) throws ServiceException;

    @RequestMapping(value = "/institution-accounts/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<InstitutionAccountDto> get(@PathVariable long id) throws ServiceException;

    @RequestMapping(value = "/institution-accounts",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<InstitutionAccountDto> update(@Valid @RequestBody InstitutionAccountDto institutionAccountDto) throws ServiceException;

    @RequestMapping(value = "/institution-accounts",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<InstitutionAccountDto> create(@Valid @RequestBody InstitutionAccountDto institutionAccountDto) throws ServiceException;

    @RequestMapping(value = "/institution-accounts/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> delete(@PathVariable long id) throws ServiceException;
}
