package com.fpo.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpo.web.entities.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {

	Document findByDocCode(String incorporation);

}
