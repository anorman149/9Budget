package com.ninebudget.service;

import com.ninebudget.model.Credential;
import com.ninebudget.model.dto.CredentialDto;
import com.ninebudget.model.mapper.CredentialMapper;
import com.ninebudget.repository.CredentialRepository;
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
 * Service Implementation for managing {@link Credential}.
 */
@Service
@Transactional
public class CredentialService {

    private final Logger log = LoggerFactory.getLogger(CredentialService.class);

    private CredentialRepository credentialRepository;

    private CredentialMapper credentialMapper;

    public CredentialService() {
    }

    public CredentialService(CredentialRepository credentialRepository, CredentialMapper credentialMapper) {
        this.credentialRepository = credentialRepository;
        this.credentialMapper = credentialMapper;
    }

    /**
     * Save a credential.
     *
     * @param credentialDto the entity to save.
     * @return the persisted entity.
     */
    public CredentialDto save(CredentialDto credentialDto) {
        log.debug("Request to save Credential : {}", credentialDto);
        Credential credential = credentialMapper.toEntity(credentialDto);
        credential = credentialRepository.save(credential);
        return credentialMapper.toDto(credential);
    }

    /**
     * Get all the credentials.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CredentialDto> findAll(Pageable pageable) {
        log.debug("Request to get all Credentials");
        return credentialRepository.findAll(pageable)
            .map(credentialMapper::toDto);
    }



    /**
    *  Get all the credentials where Institution is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CredentialDto> findAllWhereInstitutionIsNull() {
        log.debug("Request to get all credentials where Institution is null");
        return StreamSupport
            .stream(credentialRepository.findAll().spliterator(), false)
            .filter(credential -> credential.getInstitutionAccount() == null)
            .map(credentialMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one credential by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CredentialDto> findOne(Long id) {
        log.debug("Request to get Credential : {}", id);
        return credentialRepository.findById(id)
            .map(credentialMapper::toDto);
    }

    /**
     * Delete the credential by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Credential : {}", id);
        credentialRepository.deleteById(id);
    }
}
