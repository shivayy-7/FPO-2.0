package com.fpo.web.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

import com.fpo.web.entities.FarmerDocument;

public interface FarmerDocumentRepository extends JpaRepository<FarmerDocument, Long> {

	List<FarmerDocument> findByFpoIdFpoId(Long fpoId);

	FarmerDocument findByDocIdDocCode(String incorporation);

	FarmerDocument findByDocIdDocCodeAndFpoIdFpoId(String incorporation, Long fpoId);

}
