package com.ninebudget.service;

import com.ninebudget.model.Account;
import com.ninebudget.model.dto.AccountDto;
import com.ninebudget.model.mapper.AccountMapper;
import com.ninebudget.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

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
        log.debug("Request to save Account : {}", accountDto);
        Account account = accountMapper.toEntity(accountDto);
        account = accountRepository.save(account);
        return accountMapper.toDto(account);
    }

    /**
     * Get one systemAccount by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AccountDto> findOne(UUID id) {
        log.debug("Request to get Account : {}", id);
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return accountRepository.findById(id)
                .filter(account -> account.getId().toString().equals(principal.getUsername()))
                .map(accountMapper::toDto);
    }

    /**
     * Delete the systemAccount by id.
     *
     * @param id the id of the entity.
     */
    public void delete(UUID id) {
        log.debug("Request to delete Account : {}", id);
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //Only delete if User has access
        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent() && account.get().getId().toString().equals(principal.getUsername())) {
            accountRepository.deleteById(id);
        }
    }
}
