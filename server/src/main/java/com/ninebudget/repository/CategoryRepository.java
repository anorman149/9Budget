package com.ninebudget.repository;

import com.ninebudget.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


/**
 * Spring Data  repository for the Category entity.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

}
