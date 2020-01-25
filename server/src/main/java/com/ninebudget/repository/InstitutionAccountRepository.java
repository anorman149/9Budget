package com.ninebudget.repository;

import com.ninebudget.model.InstitutionAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the InstitutionAccount entity.
 */
@Repository
public interface InstitutionAccountRepository extends JpaRepository<InstitutionAccount, Long> {
}
