package com.fpo.web.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fpo.web.entities.Scheme;

@Repository
public interface SchemeRepository extends JpaRepository<Scheme, Long> {
	
	List<Scheme> findByIsActiveTrueOrderBySchemeNameEnAsc();

	List<Scheme> findAllByOrderBySchemeNameEnAsc();

	
//	Scheme findBySchemeCode(String schemeCode);

	@Query(value="select * from t_mst_scheme tms where tms.scheme_code=:schemeCode ", nativeQuery = true)
	Scheme getBySchemeCode(String schemeCode);
	Scheme findBySchemeId(Long schemeId);

	List<Scheme> findByIsActive(boolean b);

	List<Scheme> findByIsActiveTrueOrderBySchemeNameEn();

}