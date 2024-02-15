package com.fpo.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fpo.web.entities.Block;

import java.util.List;

@Repository
public interface BlockRepository extends JpaRepository<Block, Long> {

	@Query("FROM Block where isActive = :isActive")
	List<Block> findAll(@Param("isActive") Boolean isActive);

	@Query("FROM Block where district.districtId =:districtId and isActive = :isActive")
	List<Block> findAllByDistrictId(@Param("districtId") Long districtId, @Param("isActive") Boolean isActive);

	Block findByBlockCode(String code);

//	Block findByBlockNameEN(String name);

	List<Block> findAllByDistrictDistrictIdAndIsActiveOrderByBlockId(Long districtId, boolean b);

    Block findByBlockId(Long blockId);

//	List<Block> findAllByOrderByBlockNameEN();

//	List<Block> findAllByOrderByDistrictDistrictNameEN();

	@Query(value="select * from t_mst_block tmb \r\n"
			+ "join t_mst_district tmd on tmd.district_id = tmb.district_id \r\n"
			+ "order by tmd.district_name_en  , tmb.block_name_en", nativeQuery = true)
	List<Block> findAllBlock();

	List<Block> findAllByIsActiveTrue();
}
