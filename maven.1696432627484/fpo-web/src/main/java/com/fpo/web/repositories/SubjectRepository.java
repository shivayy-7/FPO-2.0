package com.fpo.web.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpo.web.entities.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

	List<Subject> findAllByIsActive(boolean b);

}
