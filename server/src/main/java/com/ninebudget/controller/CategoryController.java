package com.ninebudget.controller;

import com.ninebudget.model.APIController;
import com.ninebudget.model.CategoryOperations;
import com.ninebudget.model.ServiceException;
import com.ninebudget.model.dto.CategoryDto;
import com.ninebudget.service.CategoryService;
import com.ninebudget.util.ResponseUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@APIController
public class CategoryController implements CategoryOperations {
    private static final Logger log = LogManager.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @Override
    public ResponseEntity<List<CategoryDto>> getAll(Pageable pageable, String filter) throws ServiceException {
        if ("transaction-is-null".equals(filter)) {
            log.debug("REST request to get all Categories where transaction is null");
            return new ResponseEntity<>(categoryService.findAllWhereTransactionIsNull(), HttpStatus.OK);
        }

        log.debug("REST request to get a page of Categories");

        Page<CategoryDto> page = categoryService.findAll(pageable);

        return ResponseEntity.ok().body(page.getContent());
    }

    @Override
    public ResponseEntity<CategoryDto> get(long id) throws ServiceException {
        log.debug("REST request to get Category : {}", id);

        Optional<CategoryDto> categoryDto = categoryService.findOne(id);

        return ResponseUtil.wrapOrNotFound(categoryDto);
    }

    @Override
    public ResponseEntity<CategoryDto> update(CategoryDto category) throws ServiceException {
        log.debug("REST request to update Category : {}", category);

        CategoryDto result = categoryService.save(category);

        URI uri;
        try{
            uri = new URI(String.valueOf(result.getId()));
        } catch (URISyntaxException e) {
            throw new ServiceException(e);
        }

        return ResponseEntity.created(uri).body(result);
    }

    @Override
    public ResponseEntity<CategoryDto> create(CategoryDto category) throws ServiceException {
        log.debug("REST request to create Category : {}", category);

        CategoryDto result = categoryService.save(category);

        URI uri;
        try{
            uri = new URI(String.valueOf(result.getId()));
        } catch (URISyntaxException e) {
            throw new ServiceException(e);
        }

        return ResponseEntity.created(uri).body(result);
    }

    @Override
    public ResponseEntity<Void> delete(long id) throws ServiceException {
        log.debug("REST request to delete Category : {}", id);

        categoryService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
