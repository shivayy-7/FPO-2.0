package com.fpo.web.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpo.web.entities.Designation;

public interface DesignationRepository extends JpaRepository<Designation, Long> {

	List<Designation> findAllByIsActiveTrue();

	Designation findByDesignationId(Long designationId);

}
