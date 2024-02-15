package com.fpo.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fpo.web.entities.Municipality;

import java.util.List;

@Repository
public interface MunicipalityRepository extends JpaRepository<Municipality, Long> {

	@Query("From Municipality Where isActive=:isActive")
	List<Municipality> findAll(@Param("isActive") boolean isActive);

	@Query("From Municipality Where district.districtId=:districtId and isActive=:isActive ")
	List<Municipality> findMunicipalityByDistrictId(@Param("districtId") Long districtId,@Param("isActive") boolean isActive);

	Municipality findByMunicipalityCode(String code);

	Municipality findByMunicipalityNameEn(String name);

}
