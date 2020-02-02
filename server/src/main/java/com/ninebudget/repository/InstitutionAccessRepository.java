package com.ninebudget.repository;

import com.ninebudget.model.InstitutionAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the InstitutionAccess entity.
 */
@Repository
public interface InstitutionAccessRepository extends JpaRepository<InstitutionAccess, Long> {
}
