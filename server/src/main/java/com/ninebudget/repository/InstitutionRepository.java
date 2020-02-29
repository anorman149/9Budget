package com.ninebudget.repository;

import com.ninebudget.model.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Spring Data  repository for the Institution entity.
 */
@Repository
public interface InstitutionRepository extends JpaRepository<Institution, UUID> {

}
