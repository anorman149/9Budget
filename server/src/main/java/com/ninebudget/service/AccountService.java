package com.ninebudget.service;

import com.ninebudget.model.Account;
import com.ninebudget.model.dto.AccountDto;
import com.ninebudget.model.mapper.AccountMapper;
import com.ninebudget.repository.AccountRepository;
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
 * Service Implementation for managing {@link Account}.
 */
@Service
@Transactional
public class AccountService {

    private final Logger log = LoggerFactory.getLogger(AccountService.class);

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;

    public AccountService(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    /**
     * Save a systemAccount.
     *
     * @param accountDto the entity to save.
     * @return the persisted entity.
     */
    public AccountDto save(AccountDto accountDto) {
        log.debug("Request to save SystemAccount : {}", accountDto);
        Account account = accountMapper.toEntity(accountDto);
        account = accountRepository.save(account);
        return accountMapper.toDto(account);
    }

    /**
     * Get all the systemAccounts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<AccountDto> findAll(Pageable pageable) {
        log.debug("Request to get all SystemAccounts");
        return accountRepository.findAll(pageable)
            .map(accountMapper::toDto);
    }



    /**
    *  Get all the systemAccounts where Category is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AccountDto> findAllWhereCategoryIsNull() {
        log.debug("Request to get all systemAccounts where Category is null");
        return StreamSupport
            .stream(accountRepository.findAll().spliterator(), false)
            .filter(systemAccount -> systemAccount.getCategory() == null)
            .map(accountMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
    *  Get all the systemAccounts where Budget is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AccountDto> findAllWhereBudgetIsNull() {
        log.debug("Request to get all systemAccounts where Budget is null");
        return StreamSupport
            .stream(accountRepository.findAll().spliterator(), false)
            .filter(systemAccount -> systemAccount.getBudgets() == null)
            .map(accountMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
    *  Get all the systemAccounts where Institution is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AccountDto> findAllWhereInstitutionIsNull() {
        log.debug("Request to get all systemAccounts where Institution is null");
        return StreamSupport
            .stream(accountRepository.findAll().spliterator(), false)
            .filter(systemAccount -> systemAccount.getInstitutionAccount() == null)
            .map(accountMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one systemAccount by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AccountDto> findOne(Long id) {
        log.debug("Request to get SystemAccount : {}", id);
        return accountRepository.findById(id)
            .map(accountMapper::toDto);
    }

    /**
     * Delete the systemAccount by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete SystemAccount : {}", id);
        accountRepository.deleteById(id);
    }
}
