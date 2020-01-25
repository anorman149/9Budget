package com.ninebudget.service;

import com.ninebudget.model.Institution;
import com.ninebudget.model.dto.InstitutionDto;
import com.ninebudget.model.mapper.InstitutionMapper;
import com.ninebudget.repository.InstitutionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Institution}.
 */
@Service
@Transactional
public class InstitutionService {
    private final Logger log = LoggerFactory.getLogger(InstitutionService.class);

    private final InstitutionRepository institutionRepository;

    private final InstitutionMapper institutionMapper;

    public InstitutionService(InstitutionRepository institutionRepository, InstitutionMapper institutionMapper) {
        this.institutionRepository = institutionRepository;
        this.institutionMapper = institutionMapper;
    }

    /**
     * Save a institution.
     *
     * @param institutionDto the entity to save.
     * @return the persisted entity.
     */
    public InstitutionDto save(InstitutionDto institutionDto) {
        log.debug("Request to save Institution : {}", institutionDto);
        Institution institution = institutionMapper.toEntity(institutionDto);
        institution = institutionRepository.save(institution);
        return institutionMapper.toDto(institution);
    }

    /**
     * Get all the institutions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<InstitutionDto> findAll(Pageable pageable) {
        log.debug("Request to get all Institutions");
        return institutionRepository.findAll(pageable)
            .map(institutionMapper::toDto);
    }


    /**
     * Get one institution by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InstitutionDto> findOne(Long id) {
        log.debug("Request to get Institution : {}", id);
        return institutionRepository.findById(id)
            .map(institutionMapper::toDto);
    }

    /**
     * Delete the institution by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Institution : {}", id);
        institutionRepository.deleteById(id);
    }
}
