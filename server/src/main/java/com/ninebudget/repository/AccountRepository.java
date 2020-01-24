package com.ninebudget.repository;

import com.ninebudget.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for the SystemAccount entity.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}
