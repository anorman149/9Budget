package com.ninebudget.service;

import com.ninebudget.model.Budget;
import com.ninebudget.model.dto.BudgetDto;
import com.ninebudget.model.mapper.BudgetMapper;
import com.ninebudget.repository.BudgetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Budget}.
 */
@Service
@Transactional
public class BudgetService {
    private final Logger log = LoggerFactory.getLogger(BudgetService.class);

    private final BudgetRepository budgetRepository;

    private final BudgetMapper budgetMapper;

    public BudgetService(BudgetRepository budgetRepository, BudgetMapper budgetMapper) {
        this.budgetRepository = budgetRepository;
        this.budgetMapper = budgetMapper;
    }

    /**
     * Save a budget.
     *
     * @param budgetDto the entity to save.
     * @return the persisted entity.
     */
    public BudgetDto save(BudgetDto budgetDto) {
        log.debug("Request to save Budget : {}", budgetDto);
        Budget budget = budgetMapper.toEntity(budgetDto);
        budget = budgetRepository.save(budget);
        return budgetMapper.toDto(budget);
    }

    /**
     * Get all the budgets.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BudgetDto> findAll() {
        log.debug("Request to get all Budgets");
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return budgetRepository.findAll().stream()
                .filter(budget -> budget.getAccount().getId().toString().equals(principal.getUsername()))
                .map(budgetMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one budget by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BudgetDto> findOne(UUID id) {
        log.debug("Request to get Budget : {}", id);
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return budgetRepository.findById(id)
                .filter(budget -> budget.getAccount().getId().toString().equals(principal.getUsername()))
                .map(budgetMapper::toDto);
    }

    /**
     * Delete the budget by id.
     *
     * @param id the id of the entity.
     */
    public void delete(UUID id) {
        log.debug("Request to delete Budget : {}", id);
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //Only delete if User has access
        Optional<Budget> budget = budgetRepository.findById(id);
        if (budget.isPresent() && budget.get().getAccount().getId().toString().equals(principal.getUsername())) {
            budgetRepository.deleteById(id);
        }
    }
}
