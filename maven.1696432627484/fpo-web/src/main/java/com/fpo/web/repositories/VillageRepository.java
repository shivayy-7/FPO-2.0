package com.fpo.web.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fpo.web.entities.Village;

import java.util.List;

@Repository
public interface VillageRepository extends JpaRepository<Village, Long> {

	@Query("FROM Village where gpId.gpId=:gpId and isActive=:isActive")
	List<Village> findAllByBlockId(@Param("gpId") Long gpId, @Param("isActive") Boolean isActive);

	Village findByVillageCode(String code);

	Village findByVillageNameEn(String name);

	List<Village> findAllByGpIdGpIdAndIsActive(Long gpId, boolean b);
	
	
	@Query(value = "select * from t_mst_village tmv \r\n"
			+ "join t_mst_gramapanchayat tmg on tmg.gp_id =tmv.gp_id \r\n"
			+ "join t_mst_block tmb on tmb.block_id =tmg.block_id \r\n"
			+ "join t_mst_district tmd  on tmd.district_id =tmb.district_id \r\n"
			+ "where tmd.district_id =:districtId",nativeQuery =true)
	List<Village> findAllByDistictid(@Param("districtId")Long districtId);

	Village findByVillageCode(String[] villageCode);

	List<Village> findByGpIdGpIdIn(List<Long> gpIdList);

    Village findByVillageId(Long villageId);

    List<Village> findAllByGpIdGpIdInAndIsActive(List<Long> collect, boolean b);

	@Query(value = "select * from t_mst_village v\r\n"
			+ "join t_mst_gramapanchayat g on g.gp_id = v.gp_id \r\n"
			+ "join t_mst_block b on b.block_id = g.block_id \r\n"
			+ "join t_mst_district d on d.district_id = b.district_id \r\n"
			+ "order by d.district_name_en , b.block_name_en , g.gp_name_en , v.village_name_en",nativeQuery =true)
	List<Village> findAllVillage();

	@Query(value = "select\r\n"
			+ "	*\r\n"
			+ "from\r\n"
			+ "	t_mst_village v\r\n"
			+ "join t_mst_gramapanchayat g on\r\n"
			+ "	g.gp_id = v.gp_id\r\n"
			+ "join t_mst_block b on\r\n"
			+ "	b.block_id = g.block_id\r\n"
			+ "join t_mst_district d on\r\n"
			+ "	d.district_id = b.district_id\r\n"
			+ "where\r\n"
			+ "	case\r\n"
			+ "		when :districtId != 0 then d.district_id =:districtId\r\n"
			+ "		else d.district_id is not null\r\n"
			+ "	end\r\n"
			+ "	and case\r\n"
			+ "		when :blockId != 0 then b.block_id =:blockId\r\n"
			+ "		else b.block_id is not null\r\n"
			+ "	end\r\n"
			+ "	and case\r\n"
			+ "		when :gpId != 0 then g.gp_id =:gpId\r\n"
			+ "		else g.gp_id is not null\r\n"
			+ "	end\r\n"
			+ "	and case\r\n"
			+ "		when :villageId != 0 then v.village_id =:villageId\r\n"
			+ "		else v.village_id is not null\r\n"
			+ "	end\r\n"
			+ "	group by d.district_name_en , d.district_id , b.block_name_en , b.block_id ,\r\n"
			+ "	g.gp_name_en , g.gp_id , v.village_name_en ,v.village_id ",nativeQuery =true)
	List<Village> findAllFilterList(@Param("districtId")Long districtId, @Param("blockId")Long blockId,@Param("gpId") Long gpId, @Param("villageId")Long villageId);

	List<Village> findAllByVillageIdInAndIsActiveTrue(List<Long> collect);
}
