package com.ninebudget.service;

import com.ninebudget.model.Transaction;
import com.ninebudget.model.dto.TransactionDto;
import com.ninebudget.model.mapper.TransactionMapper;
import com.ninebudget.repository.TransactionRepository;
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
 * Service Implementation for managing {@link Transaction}.
 */
@Service
@Transactional
public class TransactionService {

    private final Logger log = LoggerFactory.getLogger(TransactionService.class);

    private final TransactionRepository transactionRepository;

    private final TransactionMapper transactionMapper;

    public TransactionService(TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    /**
     * Save a transaction.
     *
     * @param transactionDto the entity to save.
     * @return the persisted entity.
     */
    public TransactionDto save(TransactionDto transactionDto) {
        log.debug("Request to save Transaction : {}", transactionDto);
        Transaction transaction = transactionMapper.toEntity(transactionDto);
        transaction = transactionRepository.saveAndFlush(transaction);
        return transactionMapper.toDto(transaction);
    }

    /**
     * Get all the transactions.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TransactionDto> findAll() {
        log.debug("Request to get all Transactions");
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return transactionRepository.findAll().stream()
                .filter(trans -> trans.getAccount().getId().toString().equals(principal.getUsername()))
                .map(transactionMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one transaction by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TransactionDto> findOne(UUID id) {
        log.debug("Request to get Transaction : {}", id);
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return transactionRepository.findById(id)
                .filter(trans -> trans.getAccount().getId().toString().equals(principal.getUsername()))
                .map(transactionMapper::toDto);
    }

    /**
     * Delete the transaction by id.
     *
     * @param id the id of the entity.
     */
    public void delete(UUID id) {
        log.debug("Request to delete Transaction : {}", id);
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //Only delete if User has access
        Optional<Transaction> trans = transactionRepository.findById(id);
        if (trans.isPresent() && trans.get().getAccount().getId().toString().equals(principal.getUsername())) {
            transactionRepository.deleteById(id);
        }
    }
}
