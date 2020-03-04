package com.ninebudget.model;

import com.ninebudget.model.dto.TransactionDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

public interface TransactionOperations {
    @RequestMapping(value = "/transactions",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<TransactionDto>> getAll(@RequestParam(required = false) String filter) throws ServiceException;

    @RequestMapping(value = "/transactions/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<TransactionDto> get(@PathVariable UUID id) throws ServiceException;

    @RequestMapping(value = "/transactions",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<TransactionDto> update(@Valid @RequestBody TransactionDto transactionDto) throws ServiceException;

    @RequestMapping(value = "/transactions",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<TransactionDto> create(@Valid @RequestBody TransactionDto transactionDto) throws ServiceException;

    @RequestMapping(value = "/transactions/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> delete(@PathVariable UUID id) throws ServiceException;
}
