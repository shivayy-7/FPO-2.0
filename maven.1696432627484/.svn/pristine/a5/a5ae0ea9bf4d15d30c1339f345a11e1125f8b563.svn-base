package com.fpo.web.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fpo.web.entities.Component;
import com.fpo.web.entities.MapActivity;

@Repository
public interface MapActivityRepository extends JpaRepository<MapActivity, Long> {

    List<MapActivity> findByActCodeActCode(String actCode);

    List<MapActivity> findByComponentCmpCodeAndIsActiveTrue(String cmpCode);

    List<MapActivity> findByActCodeActCodeAndIsActiveTrue(String actCode);

    MapActivity findByActCodeActCodeAndComponentCmpCode(String actCode, String parentCode);

	@Query("select m from MapActivity m where m.component.cmpCode = :cmpCode and m.isActive = true order by m.id DESC")
	List<MapActivity> getActivityByCompId(@Param("cmpCode") String cmpCode);

	MapActivity findAllByActCodeActCode(String childCode);

	Component findByActCodeIdAndIsActiveTrue(Long id2);


}