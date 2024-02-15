package com.fpo.web.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpo.web.entities.Ward;

import java.util.List;

@Repository
public interface WardRepository extends JpaRepository<Ward, Long>{

	List<Ward> findAllByMunicipalityMunicipalityIdAndIsActiveTrue(Long ulbId);
	

}
