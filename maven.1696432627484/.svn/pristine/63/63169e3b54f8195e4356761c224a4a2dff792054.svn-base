package com.fpo.web.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpo.web.entities.PhysicalUnit;

public interface PhysicalUnitRepository extends JpaRepository<PhysicalUnit, Long> {
	
    PhysicalUnit findByUnitCode(String childUnit);

	List<PhysicalUnit> findAllByIsActiveTrue();
}