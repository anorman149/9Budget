package com.ninebudget.model;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

public interface InstitutionOperations {
    @RequestMapping(value = "/institutions",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Institution> getAll() throws ServiceException;

    @RequestMapping(value = "/institutions/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    Institution get(@PathVariable int id) throws ServiceException;

    @RequestMapping(value = "/institutions",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    void update(@Valid @RequestBody Institution institution) throws ServiceException;

    @RequestMapping(value = "/institutions",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    void create(@Valid @RequestBody Institution institution) throws ServiceException;

    @RequestMapping(value = "/institutions/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    void delete(@PathVariable int id) throws ServiceException;
}
