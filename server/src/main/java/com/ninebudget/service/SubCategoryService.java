package com.ninebudget.service;

import com.ninebudget.model.SubCategory;
import com.ninebudget.model.dto.SubCategoryDto;
import com.ninebudget.model.mapper.SubCategoryMapper;
import com.ninebudget.repository.SubCategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SubCategory}.
 */
@Service
@Transactional
public class SubCategoryService {

    private final Logger log = LoggerFactory.getLogger(SubCategoryService.class);

    private SubCategoryRepository subCategoryRepository;

    private SubCategoryMapper subCategoryMapper;

    public SubCategoryService() {
    }

    public SubCategoryService(SubCategoryRepository subCategoryRepository, SubCategoryMapper subCategoryMapper) {
        this.subCategoryRepository = subCategoryRepository;
        this.subCategoryMapper = subCategoryMapper;
    }

    /**
     * Save a subCategory.
     *
     * @param subCategoryDto the entity to save.
     * @return the persisted entity.
     */
    public SubCategoryDto save(SubCategoryDto subCategoryDto) {
        log.debug("Request to save SubCategory : {}", subCategoryDto);
        SubCategory subCategory = subCategoryMapper.toEntity(subCategoryDto);
        subCategory = subCategoryRepository.save(subCategory);
        return subCategoryMapper.toDto(subCategory);
    }

    /**
     * Get all the subCategories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<SubCategoryDto> findAll(Pageable pageable) {
        log.debug("Request to get all SubCategories");
        return subCategoryRepository.findAll(pageable)
            .map(subCategoryMapper::toDto);
    }


    /**
     * Get one subCategory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SubCategoryDto> findOne(Long id) {
        log.debug("Request to get SubCategory : {}", id);
        return subCategoryRepository.findById(id)
            .map(subCategoryMapper::toDto);
    }

    /**
     * Delete the subCategory by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete SubCategory : {}", id);
        subCategoryRepository.deleteById(id);
    }
}
