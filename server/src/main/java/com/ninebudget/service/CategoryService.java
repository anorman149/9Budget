package com.ninebudget.service;

import com.ninebudget.model.Category;
import com.ninebudget.model.CategoryType;
import com.ninebudget.model.dto.CategoryDto;
import com.ninebudget.model.mapper.CategoryMapper;
import com.ninebudget.repository.CategoryRepository;
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
 * Service Implementation for managing {@link Category}.
 */
@Service
@Transactional
public class CategoryService {

    private final Logger log = LoggerFactory.getLogger(CategoryService.class);

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    /**
     * Save a category.
     *
     * @param categoryDto the entity to save.
     * @return the persisted entity.
     */
    public CategoryDto save(CategoryDto categoryDto) {
        log.debug("Request to save Category : {}", categoryDto);
        Category category = categoryMapper.toEntity(categoryDto);
        category = categoryRepository.save(category);
        return categoryMapper.toDto(category);
    }

    /**
     * Get all the categories.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CategoryDto> findAll() {
        log.debug("Request to get all Categories");
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return categoryRepository.findAll().stream()
                .filter(category -> category.getAccounts().stream().anyMatch(account -> account.getId().toString().equals(principal.getUsername())))
                .map(categoryMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the categories where Transaction is {@code null}.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CategoryDto> findAllWhereTransactionIsNull() {
        log.debug("Request to get all categories where Transaction is null");
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return categoryRepository.findAll().stream()
                .filter(category -> category.getAccounts().stream().anyMatch(account -> account.getId().toString().equals(principal.getUsername())))
                .map(categoryMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one category by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CategoryDto> findOne(UUID id) {
        log.debug("Request to get Category : {}", id);
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return categoryRepository.findById(id)
                .filter(category -> category.getAccounts().stream().anyMatch(account -> account.getId().toString().equals(principal.getUsername())))
                .map(categoryMapper::toDto);
    }

    /**
     * Delete the category by id.
     *
     * @param id the id of the entity.
     */
    public void delete(UUID id) {
        log.debug("Request to delete Category : {}", id);
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //Only delete if User has access
        Optional<Category> cat = categoryRepository.findById(id);
        if (cat.isPresent() && cat.get().getAccounts().stream().anyMatch(account -> account.getId().toString().equals(principal.getUsername()))) {
            categoryRepository.deleteById(id);
        }
    }

    /**
     * Get all the categories where Transaction is {@code null}.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public CategoryDto findAllWhereTypeIs(CategoryType categoryType, UUID accountId) {
        log.debug("Request to get all categories where category type is: {}", categoryType);
        return categoryRepository.findAll().stream()
                .filter(category -> category.getType().equals(categoryType) &&
                        category.getAccounts().stream().anyMatch(account -> account.getId().equals(accountId)))
                .map(categoryMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new))
                .getFirst();
    }
}
