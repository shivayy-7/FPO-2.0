package com.fpo.web.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fpo.web.entities.Activity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

	Activity findByActCodeAndIsActiveTrue(String childCode);

	Activity findByActCode(String childCode);

	@Query("select a from Activity a where a.actCode = :actCode and a.isActive = true")
	Optional<Activity> getByIdAndIsActiveIsTrue(@Param("actCode") String actCode);

	Activity findByActNameEn(String actName);

	List<Activity> findAllByIsActiveTrue();





}