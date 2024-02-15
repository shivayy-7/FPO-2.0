package com.fpo.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpo.web.entities.MapFunctionalHead;

import java.util.List;
import java.util.Optional;

@Repository
public interface MapFunctionalHeadRepository extends JpaRepository<MapFunctionalHead, Long> {
    List<MapFunctionalHead> findByHeadCodeHeadCode(String headCode);

    List<MapFunctionalHead> findByIdAndIsActiveTrue(Long childId);

	List<MapFunctionalHead> findByHeadCodeHeadCodeAndIsActiveTrue(String headCode);

	MapFunctionalHead findByHeadCodeHeadCodeAndCmpCodeCmpCode(String headCode, String parentCode);





}