package com.ninebudget.service;

import com.ninebudget.model.InstitutionAccount;
import com.ninebudget.model.dto.InstitutionAccountDto;
import com.ninebudget.model.mapper.InstitutionAccountMapper;
import com.ninebudget.repository.InstitutionAccountRepository;
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
        institutionAccount = institutionAccountRepository.saveAndFlush(institutionAccount);
        return institutionAccountMapper.toDto(institutionAccount);
    }

    /**
     * Get all the institutionAccounts.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<InstitutionAccountDto> findAll() {
        log.debug("Request to get all InstitutionAccounts");
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return institutionAccountRepository.findAll().stream()
                .filter(account -> account.getAccount().getId().toString().equals(principal.getUsername()))
                .map(institutionAccountMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one institutionAccount by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InstitutionAccountDto> findOne(UUID id) {
        log.debug("Request to get InstitutionAccount : {}", id);
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return institutionAccountRepository.findById(id)
                .filter(account -> account.getAccount().getId().toString().equals(principal.getUsername()))
                .map(institutionAccountMapper::toDto);
    }

    /**
     * Delete the institutionAccount by id.
     *
     * @param id the id of the entity.
     */
    public void delete(UUID id) {
        log.debug("Request to delete InstitutionAccount : {}", id);
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //Only delete if User has access
        Optional<InstitutionAccount> trans = institutionAccountRepository.findById(id);
        if (trans.isPresent() && trans.get().getAccount().getId().toString().equals(principal.getUsername())) {
            institutionAccountRepository.deleteById(id);
        }
    }
}
