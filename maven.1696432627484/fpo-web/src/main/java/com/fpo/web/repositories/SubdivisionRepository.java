package com.fpo.web.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fpo.web.entities.Subdivision;

@Repository
public interface SubdivisionRepository extends JpaRepository<Subdivision, Long> {

	@Query("FROM Subdivision WHERE district.districtId=:districtId AND isActive=true order by subdivisionName") 
	List<Subdivision> findAllActiveSubdivisionByDistrictId(@Param("districtId")Long districtId);

	Subdivision findBySubdivisionId(Long subdivisionId); 
	
//	@Query("FROM Subdivision WHERE isActive=true order by district.districtName")
//	List<Subdivision> findAllActiveSubdivision();

	Subdivision findBySubdivisionCode(String subdivisionCode);

}
	