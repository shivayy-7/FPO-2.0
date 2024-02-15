package com.fpo.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpo.web.entities.FunctionalHead;

import java.util.List;

@Repository
public interface FunctionalHeadRepository extends JpaRepository<FunctionalHead, Long> {

	FunctionalHead findByHeadCodeAndIsActiveTrue(String parentCode);

    List<FunctionalHead> findAllByIsActiveTrue();

	FunctionalHead findByHeadCode(String childCode);


}