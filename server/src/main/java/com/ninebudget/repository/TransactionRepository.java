package com.ninebudget.repository;

import com.ninebudget.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Spring Data  repository for the Transaction entity.
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

}
