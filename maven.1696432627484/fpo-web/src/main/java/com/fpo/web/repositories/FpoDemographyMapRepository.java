package com.fpo.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpo.web.entities.FpoDemographyMap;

public interface FpoDemographyMapRepository extends JpaRepository<FpoDemographyMap, Long> {

	FpoDemographyMap findByFpoIdFpoId(Long fpoId);

}
