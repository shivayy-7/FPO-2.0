package com.fpo.web.repositories;

import com.fpo.web.entities.FarmerInstallment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FarmerInstallmentRepository extends JpaRepository<FarmerInstallment, Long> {
    List<FarmerInstallment> findAllByIsActive(boolean b);
}