package com.fpo.web.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpo.web.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByCmpCodeCmpCodeAndCategoryCode(String cmpCode, String aapType);

    Category findByCmpCodeCmpCodeAndIsStateApplicableTrueAndIsActiveTrue(String cmpCode);

    Category findByCmpCodeCmpCodeAndIsDistApplicableTrueAndIsActiveTrue(String cmpCode);

	List<Category> findAllByIsActiveTrue();

	List<Category> findAllByCmpCodeCmpCode(String mstCode);
}