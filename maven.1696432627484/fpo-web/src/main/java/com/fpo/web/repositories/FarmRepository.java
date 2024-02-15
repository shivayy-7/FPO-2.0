package com.fpo.web.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fpo.web.entities.Farm;

public interface FarmRepository extends JpaRepository<Farm, Long> {

	@Query(value = "SELECT * FROM t_frm_farm tfarm " +
	        "JOIN t_frm_farmer tff ON tff.farmer_id = tfarm.farmer_id " +
	        "JOIN t_mst_village tmv ON tmv.village_id = tff.village_id " +
	        "JOIN t_mst_gramapanchayat tmg ON tmg.gp_id = tmv.gp_id " +
	        "WHERE " +
	        "(:blockId = 0 OR tmg.block_id = :blockId) " +
	        "AND (:gpId = 0 OR tmg.gp_id = :gpId) " +
	        "AND (:villageId = 0 OR tff.village_id = :villageId) " +
	        "AND (:frmId = 0 OR tff.farmer_id = :frmId)", nativeQuery = true)
	List<Farm> getFarmData(@Param("blockId") Long blockId, 
	                       @Param("gpId") Long gpId, 
	                       @Param("villageId") Long villageId, 
	                       @Param("frmId") Long frmId);

	Farm findByFarmCode(String farmCode);


}
