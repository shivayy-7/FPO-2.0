package com.fpo.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpo.web.entities.MailTemplatesMaster;


@Repository
public interface MailTemplateRepository extends JpaRepository<MailTemplatesMaster, Long> {

	MailTemplatesMaster findByTemplateTypeAndTemplateCode(String templateType,String templateCode);

}
