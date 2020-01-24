package com.ninebudget.repository;

import com.ninebudget.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SubCategory entity.
 */
@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

}
