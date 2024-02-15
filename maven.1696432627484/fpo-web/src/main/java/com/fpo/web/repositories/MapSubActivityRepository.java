package com.fpo.web.repositories;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fpo.web.entities.MapSubActivity;

@Repository
public interface MapSubActivityRepository extends JpaRepository<MapSubActivity, Long> {

//	List<MapSubActivity> findBySubActCodeSubActCode(String subActCode);

    List<MapSubActivity> findByActCodeActCodeAndIsActiveTrue(String actCode);

	List<MapSubActivity> findBySubActCodeSubActCodeAndIsActiveTrue(String subActCode);

	MapSubActivity findBySubActCodeSubActCodeAndActCodeActCode(String subActCode, String parentCode);

    @Query("select m from MapSubActivity m where m.actCode.actCode = :actCode and m.isActive = true order by m.id DESC")
    List<MapSubActivity> getSubActivityByActId(@Param("actCode") String actCode);

    List<MapSubActivity> findAllByActCodeActCode(String actCode);

	MapSubActivity findAllBySubActCodeSubActCode(String childCode);

	List<MapSubActivity> findBySubActCodeSubActCode(String childCode);
}