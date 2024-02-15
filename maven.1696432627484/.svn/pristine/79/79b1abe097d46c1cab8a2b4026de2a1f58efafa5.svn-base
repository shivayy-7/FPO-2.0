package com.fpo.web.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpo.web.entities.Training;
import com.fpo.web.entities.TrainingFpoMap;

public interface TrainingFpoMapRepository extends JpaRepository<TrainingFpoMap, Long> {

	List<TrainingFpoMap> findByTrainingId(Long trainingId);

	List<TrainingFpoMap> findByTrainingIdTrainingId(Long long1);

	TrainingFpoMap findByFpoIdFpoId(Long fpoId);



}
