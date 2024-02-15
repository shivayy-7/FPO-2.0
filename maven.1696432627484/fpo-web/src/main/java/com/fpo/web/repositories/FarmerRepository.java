package com.fpo.web.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpo.web.entities.Farmer;

public interface FarmerRepository extends JpaRepository<Farmer, Long> {

	Optional<Farmer> findByFarmerCode(String farmerCode);

	List<Farmer> findByVillageIdVillageIdAndIsActive(long parseLong, boolean b);

}
