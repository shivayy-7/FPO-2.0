package com.fpo.web.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpo.web.entities.FpoManagement;

public interface FpoManagementRepository extends JpaRepository<FpoManagement, Long> {

	List<FpoManagement> findByFpoIdFpoId(Long fpoId);

	List<FpoManagement> findByFpoIdFpoIdAndIsActive(Long fpoId, boolean b);

	FpoManagement findByDirectorId(Long existingid);

}
