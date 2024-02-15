package com.fpo.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fpo.web.entities.Grampanchayat;

import java.util.List;

@Repository
public interface GrampanchayatRpository extends JpaRepository<Grampanchayat, Long> {

	@Query("FROM Grampanchayat where isActive = :isActive")
	List<Grampanchayat> findAll(@Param("isActive") Boolean isActive);

	@Query("FROM Grampanchayat where block.blockId=:blockId and isActive = :isActive")
	List<Grampanchayat> findAllByBlockId(@Param("blockId") Long blockId, @Param("isActive") Boolean isActive);

	Grampanchayat findByGpCode(String code);

	Grampanchayat findByGpNameEN(String name);

	List<Grampanchayat> findByBlockBlockIdAndIsActive(Long blockId, boolean b);

	List<Grampanchayat> findByBlockBlockIdIn(List<Long> blockList);

    Grampanchayat findByGpId(Long gpId);

    List<Grampanchayat> findAllByBlockBlockIdInAndIsActive(List<Long> blockId, boolean active);

    @Query(value="select * from t_mst_gramapanchayat g \r\n"
    		+ "join t_mst_block b on b.block_id = g.block_id \r\n"
    		+ "join t_mst_district d on d.district_id = b.district_id \r\n"
    		+ "order by d.district_name_en , b.block_name_en , g.gp_name_en", nativeQuery = true)
	List<Grampanchayat> findAllGp();

	List<Grampanchayat> findAllByBlockBlockIdAndIsActive(Long parseLong, Boolean active);
}
