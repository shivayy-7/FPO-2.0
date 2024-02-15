package com.fpo.web.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aashdit.framework.core.model.Auditable;
import com.aashdit.umt.model.User;

import lombok.Data;

@Data
@Entity
@Table(name="t_mst_subject")
public class Subject extends Auditable<User> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6669546592860350247L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="subject_id")
	private Long subjectId;
	
	@Column(name="subject_name")
	private String subjectName;
	
	@Column(name="subject_code")
	private String subjectCode;
	
	@Column(name="is_active")
	private Boolean isActive;
}
