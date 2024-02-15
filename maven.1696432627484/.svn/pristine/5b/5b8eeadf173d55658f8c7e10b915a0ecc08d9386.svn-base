package com.fpo.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpo.web.entities.Milstone;

import java.util.List;

public interface MilstoneRepository extends JpaRepository<Milstone, Long> {

    List<Milstone> findAllByInstallmentId(Long instId);
}
