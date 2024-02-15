package com.fpo.web.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpo.web.entities.Fpo;

public interface FpoRepository extends JpaRepository<Fpo, Long> {

	List<Fpo> findAllByIsActiveTrue();

	Optional<Fpo> findByFpoCode(String fpoCode);

	Optional<Fpo> findByUserIdUserId(Long userId);

}
