package com.fpo.web.repositories;


import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fpo.web.entities.Component;

@Repository
public interface ComponentRepository extends JpaRepository<Component, Long> {
	@Query("select c from Component c where c.cmpCode = :cmpCode and c.isActive = :isActive")
	Optional<Component> getByComCode(@Param("cmpCode") String cmpCode, @Param("isActive") Boolean isActive);

	List<Component> findByIsActiveTrueOrderByCmpNameEnAsc();

	List<Component> findAllByOrderByCmpNameEnAsc();

	Component findByCmpCodeAndIsActiveTrue(String parentCode);

	Component findByCmpCode(String cmpCode);

	List<Component> findByIsActive(boolean b);

	List<Component> findByIsActiveTrueOrderByCmpNameEn();

	Component findByCmpCode(Long cmpCode);

	List<Component> findAllByIsActiveTrue();


}