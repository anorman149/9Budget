package com.ninebudget.controller;

import com.ninebudget.model.*;
import com.ninebudget.model.dto.BudgetDto;
import com.ninebudget.model.dto.CategoryDto;
import com.ninebudget.service.*;
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
public class BudgetController implements BudgetOperations {
    private static final Logger log = LogManager.getLogger(BudgetController.class);

    @Autowired
    private BudgetService budgetService;

    @Autowired
    private CategoryService categoryService;

    @Override
    public ResponseEntity<List<BudgetDto>> getAll() throws ServiceException {
        log.debug("REST request to get all Budgets");

        List<BudgetDto> page = budgetService.findAll();

        return ResponseEntity.ok().body(page);
    }

    @Override
    public ResponseEntity<BudgetDto> get(UUID id) throws ServiceException {
        log.debug("REST request to get Budget : {}", id);

        Optional<BudgetDto> budgetDto = budgetService.findOne(id);

        return ResponseUtil.wrapOrNotFound(budgetDto);
    }

    @Override
    public ResponseEntity<BudgetDto> update(BudgetDto budget) throws ServiceException {
        log.debug("REST request to update Budget : {}", budget);

        BudgetDto result = budgetService.save(budget);

        URI uri;
        try {
            uri = new URI(String.valueOf(result.getId()));
        } catch (URISyntaxException e) {
            throw new ServiceException(e);
        }

        return ResponseEntity.created(uri).body(result);
    }

    @Override
    public ResponseEntity<BudgetDto> create(BudgetDto budget) throws ServiceException {
        log.debug("REST request to create Budget : {}", budget);

        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        CategoryDto category = new CategoryDto();
        category.setType(budget.getCategory().getType());
        category.setActive(true);
        CategoryDto categoryDto = categoryService.save(category);

        budget.setCategory(categoryDto);
        budget.setAccountId(UUID.fromString(principal.getUsername()));

        BudgetDto result = budgetService.save(budget);

        URI uri;
        try {
            uri = new URI(String.valueOf(result.getId()));
        } catch (URISyntaxException e) {
            throw new ServiceException(e);
        }

        return ResponseEntity.created(uri).body(result);
    }

    @Override
    public ResponseEntity<Void> delete(UUID id) throws ServiceException {
        log.debug("REST request to delete Budget : {}", id);

        budgetService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
