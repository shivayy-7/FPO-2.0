package com.fpo.web.repositories;

import com.fpo.web.entities.FarmerCbbo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmerCbboRepository extends JpaRepository<FarmerCbbo, Long> {
//    List<FarmerCbbo> findAllByIsActive(boolean b);
//
//    FarmerCbbo findByCbboCode(String cbboCode);

//	FarmerCbbo findByUserIdUserIdAndIsActive(Long userId, boolean b);
}