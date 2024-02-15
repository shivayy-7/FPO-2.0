package com.fpo.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fpo.web.entities.SubActivity;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubActivityRepository extends JpaRepository<SubActivity, Long> {
	
	@Query("select s from SubActivity s where s.subActCode = :subActCode and s.isActive = true")
	Optional<SubActivity> getByIdAndIsActiveIsTrue(@Param("subActCode") String subActCode);

	SubActivity findBySubActCodeAndIsActiveTrue(String childCode);

	SubActivity findBySubActCode(String childCode);

	List<SubActivity> findAllByIsActiveTrue();

	SubActivity findByIdAndIsActiveTrue(String subAct);
}