package com.fpo.web.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpo.web.entities.Caste;

import java.util.List;

@Repository
public interface CasteRepository extends JpaRepository<Caste, Long> {

    List<Caste> findAllByIsActiveTrue();

	Caste findByCasteCode(String caste);

	List<Caste> findAllByIsActive(boolean b);
}
