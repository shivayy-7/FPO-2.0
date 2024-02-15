package com.fpo.web.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpo.web.entities.FpoSchemeMap;

public interface FpoSchemeMapRepository extends JpaRepository<FpoSchemeMap, Long> {

	List<FpoSchemeMap> findByFpoIdFpoIdAndIsActive(Long fpoId, boolean b);



}
