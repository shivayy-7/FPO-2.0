package com.fpo.web.repositories;

import com.fpo.web.entities.FarmerCbboMngmt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FarmerCbboMngmtRepository extends JpaRepository<FarmerCbboMngmt, Long> {
//    List<FarmerCbboMngmt> findByCbboId(Long id);
//
//	List<FarmerCbboMngmt> findByIsActiveTrueAndIsTrainer(boolean b);
//
//	FarmerCbboMngmt findByUserIdUserIdAndIsActive(Long userId, boolean b);
//
//	List<FarmerCbboMngmt> findByCbboIdAndIsActive(Long id, boolean b);
}