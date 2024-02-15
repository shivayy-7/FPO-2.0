package com.fpo.web.repositories;

import com.fpo.web.entities.MilestoneCollectorParent;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MilestoneCollectorParentRepository extends JpaRepository<MilestoneCollectorParent, Long> {

	List<MilestoneCollectorParent> findAllByIsActiveTrue();

    MilestoneCollectorParent findByIdAndIsActive(Long formId, boolean b);

    @Query("FROM MilestoneCollectorParent WHERE id=?1")
    MilestoneCollectorParent getInstallmentData(Long instId);
}