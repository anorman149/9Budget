package com.ninebudget.model;

import com.ninebudget.model.dto.CategoryDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface CategoryOperations {
    @RequestMapping(value = "/categories",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<CategoryDto>> getAll(Pageable pageable, @RequestParam(required = false) String filter) throws ServiceException;

    @RequestMapping(value = "/categories/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CategoryDto> get(@PathVariable long id) throws ServiceException;

    @RequestMapping(value = "/categories",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CategoryDto> update(@Valid @RequestBody CategoryDto category) throws ServiceException;

    @RequestMapping(value = "/categories",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CategoryDto> create(@Valid @RequestBody CategoryDto category) throws ServiceException;

    @RequestMapping(value = "/categories/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> delete(@PathVariable long id) throws ServiceException;
}
