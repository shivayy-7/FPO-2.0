package com.fpo.web.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpo.web.entities.Fpo;
import com.fpo.web.entities.FpoSubactivityMap;
import com.fpo.web.entities.SubActivity;

public interface FpoSubactivityMapRepository extends JpaRepository<FpoSubactivityMap, Long> {

	List<FpoSubactivityMap> findByFpoFpoId(Long fpoId);

	FpoSubactivityMap findByFpoAndSubActivityId(Fpo savedFpo, SubActivity findBySubActCodeAndIsActiveTrue);

	List<FpoSubactivityMap> findByFpoFpoIdAndIsActive(Long fpoId, boolean b);

}
