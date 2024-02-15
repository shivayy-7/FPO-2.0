package com.fpo.web.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fpo.web.entities.MailQueued;


@Repository
public interface MailQueuedRepository extends JpaRepository<MailQueued, Long>{

	@Query("FROM MailQueued WHERE status=:status")
	List<MailQueued> findEmailList(@Param("status")String status);

	List<MailQueued> findAllByStatus(String statuse);

}
