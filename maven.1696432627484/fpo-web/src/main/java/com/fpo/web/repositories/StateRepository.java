package com.fpo.web.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpo.web.entities.State;


@Repository
public interface StateRepository extends JpaRepository<State, Long> {

	List<State> findAllByIsActive(boolean b);

	State findByStateId(Long stateId);


}