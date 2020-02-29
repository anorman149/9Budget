package com.ninebudget.repository;

import com.ninebudget.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


/**
 * Spring Data  repository for the Budget entity.
 */
@Repository
public interface BudgetRepository extends JpaRepository<Budget, UUID> {

}
