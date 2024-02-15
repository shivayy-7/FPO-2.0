package com.fpo.web.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpo.web.entities.FarmerSubActMap;

public interface FarmerSubActMapRepository extends JpaRepository<FarmerSubActMap, Long> {

	List<FarmerSubActMap> findByFarmerFarmerId(Long farmerId);

}
