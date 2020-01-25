package com.ninebudget.service;

import com.ninebudget.model.InstitutionAccount;
import com.ninebudget.model.dto.InstitutionAccountDto;
import com.ninebudget.model.mapper.InstitutionAccountMapper;
import com.ninebudget.repository.InstitutionAccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link com.ninebudget.model.InstitutionAccount}.
 */
@Service
@Transactional
public class InstitutionAccountService {
    private final Logger log = LoggerFactory.getLogger(InstitutionAccountService.class);

    private final InstitutionAccountRepository institutionAccountRepository;

    private final InstitutionAccountMapper institutionAccountMapper;

    public InstitutionAccountService(InstitutionAccountRepository institutionAccountRepository, InstitutionAccountMapper institutionAccountMapper) {
        this.institutionAccountRepository = institutionAccountRepository;
        this.institutionAccountMapper = institutionAccountMapper;
    }

    /**
     * Save a institution.
     *
     * @param institutionAccountDto the entity to save.
     * @return the persisted entity.
     */
    public InstitutionAccountDto save(InstitutionAccountDto institutionAccountDto) {
        log.debug("Request to save InstitutionAccount : {}", institutionAccountDto);
        InstitutionAccount institutionAccount = institutionAccountMapper.toEntity(institutionAccountDto);
        institutionAccount = institutionAccountRepository.save(institutionAccount);
        return institutionAccountMapper.toDto(institutionAccount);
    }

    /**
     * Get all the institutions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<InstitutionAccountDto> findAll(Pageable pageable) {
        log.debug("Request to get all InstitutionAccounts");
        return institutionAccountRepository.findAll(pageable)
                .map(institutionAccountMapper::toDto);
    }


    /**
     * Get one institutionAccount by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InstitutionAccountDto> findOne(Long id) {
        log.debug("Request to get InstitutionAccount : {}", id);
        return institutionAccountRepository.findById(id)
                .map(institutionAccountMapper::toDto);
    }

    /**
     * Delete the institutionAccount by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete InstitutionAccount : {}", id);
        institutionAccountRepository.deleteById(id);
    }
}
