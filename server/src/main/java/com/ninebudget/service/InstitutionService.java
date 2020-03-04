package com.ninebudget.service;

import com.ninebudget.model.Institution;
import com.ninebudget.model.dto.InstitutionDto;
import com.ninebudget.model.mapper.InstitutionMapper;
import com.ninebudget.repository.InstitutionRepository;
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
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<InstitutionDto> findAll() {
        log.debug("Request to get all Institutions");
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return institutionRepository.findAll().stream()
                .filter(account -> account.getAccount().getId().toString().equals(principal.getUsername()))
                .map(institutionMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one institution by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InstitutionDto> findOne(UUID id) {
        log.debug("Request to get Institution : {}", id);
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return institutionRepository.findById(id)
                .filter(account -> account.getAccount().getId().toString().equals(principal.getUsername()))
                .map(institutionMapper::toDto);
    }

    /**
     * Delete the institution by id.
     *
     * @param id the id of the entity.
     */
    public void delete(UUID id) {
        log.debug("Request to delete Institution : {}", id);
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //Only delete if User has access
        Optional<Institution> trans = institutionRepository.findById(id);
        if (trans.isPresent() && trans.get().getAccount().getId().toString().equals(principal.getUsername())) {
            institutionRepository.deleteById(id);
        }
    }
}
