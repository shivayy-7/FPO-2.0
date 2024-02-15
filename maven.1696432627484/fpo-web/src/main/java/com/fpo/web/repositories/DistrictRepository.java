package com.fpo.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fpo.web.entities.District;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {

	@Query("FROM District where isActive = :isActive")
	List<District> findAll(@Param("isActive") Boolean isActive);

	@Query("FROM District where isActive = :isActive and state.stateId=:stateId")
	List<District> findAllByStateId(@Param("stateId") Long stateId, @Param("isActive") Boolean isActive);

	District findByDistrictId(Long districtId);

	District findByDistrictCode(String districtCode);

	@Query(value="select distinct(d.*) from t_mst_district d join t_mst_muncipality m on m.district_id =d.district_id \r\n"
			+ "where m.municipality_id in (?1) and d.is_active =true and m.is_active = true order by d.district_name_en asc;",nativeQuery = true)
	List<District> getAllDistrictByMunicipalityId(List<Long> ulbIdList);

	

	List<District> findAllByIsActiveTrue();

	List<District> findAllByStateStateIdAndIsActive(long parseLong, boolean b);

	List<District> findAllByOrderByDistrictNameEN();

	List<District> findAllByIsActive(boolean b);

//	List<District> findAllByOrderByDistrictNameEN();

}
