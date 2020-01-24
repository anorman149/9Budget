package com.ninebudget.service;

import com.ninebudget.model.Budget;
import com.ninebudget.model.dto.BudgetDto;
import com.ninebudget.model.mapper.BudgetMapper;
import com.ninebudget.repository.BudgetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<BudgetDto> findAll(Pageable pageable) {
        log.debug("Request to get all Budgets");
        return budgetRepository.findAll(pageable).map(budgetMapper::toDto);
    }

    /**
     * Get all the budgets.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BudgetDto> findAll() {
        log.debug("Request to get all Budgets");
        return StreamSupport
                .stream(budgetRepository.findAll().spliterator(), false)
                .map(budgetMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
    *  Get all the budgets where Category is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BudgetDto> findAllWhereCategoryIsNull() {
        log.debug("Request to get all budgets where Category is null");
        return StreamSupport
            .stream(budgetRepository.findAll().spliterator(), false)
            .filter(budget -> budget.getCategory() == null)
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
    public Optional<BudgetDto> findOne(Long id) {
        log.debug("Request to get Budget : {}", id);
        return budgetRepository.findById(id)
            .map(budgetMapper::toDto);
    }

    /**
     * Delete the budget by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Budget : {}", id);
        budgetRepository.deleteById(id);
    }
}
