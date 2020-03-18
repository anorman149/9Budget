package com.ninebudget.model;

import com.ninebudget.model.dto.BudgetDto;
import com.ninebudget.validator.uuid.ValidUUID;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Validated
public interface BudgetOperations {
    @RequestMapping(value = "/budgets",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<BudgetDto>> getAll() throws ServiceException;

    @RequestMapping(value = "/budgets/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BudgetDto> get(@ValidUUID @PathVariable UUID id) throws ServiceException;

    @RequestMapping(value = "/budgets",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BudgetDto> update(@Valid @RequestBody BudgetDto budget) throws ServiceException;

    @RequestMapping(value = "/budgets",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BudgetDto> create(@Valid @RequestBody BudgetDto budget) throws ServiceException;

    @RequestMapping(value = "/budgets",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> delete(@ValidUUID @RequestParam UUID id) throws ServiceException;
}
