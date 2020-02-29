package com.ninebudget.repository;

import com.ninebudget.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Spring Data repository for the SystemAccount entity.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

}
