package com.ninebudget.service;

import com.ninebudget.model.Category;
import com.ninebudget.model.dto.CategoryDto;
import com.ninebudget.model.mapper.CategoryMapper;
import com.ninebudget.repository.CategoryRepository;
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
 * Service Implementation for managing {@link Category}.
 */
@Service
@Transactional
public class CategoryService {

    private final Logger log = LoggerFactory.getLogger(CategoryService.class);

    private CategoryRepository categoryRepository;

    private CategoryMapper categoryMapper;

    public CategoryService(){}

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
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CategoryDto> findAll(Pageable pageable) {
        log.debug("Request to get all Categories");
        return categoryRepository.findAll(pageable)
            .map(categoryMapper::toDto);
    }



    /**
    *  Get all the categories where Transaction is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CategoryDto> findAllWhereTransactionIsNull() {
        log.debug("Request to get all categories where Transaction is null");
        return StreamSupport
            .stream(categoryRepository.findAll().spliterator(), false)
            .filter(category -> category.getTransactions() == null)
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
    public Optional<CategoryDto> findOne(Long id) {
        log.debug("Request to get Category : {}", id);
        return categoryRepository.findById(id)
            .map(categoryMapper::toDto);
    }

    /**
     * Delete the category by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Category : {}", id);
        categoryRepository.deleteById(id);
    }
}
