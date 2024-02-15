package com.fpo.web.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.aashdit.framework.core.model.Auditable;
import com.aashdit.umt.model.User;

import lombok.Data;

@Entity
@Table(name="t_frm_fpo_director_info")
@Data
public class FpoManagement extends Auditable<User> implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 542447263119381879L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="director_id")
	private Long directorId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="din")
	private String din;
	
	@ManyToOne
	@JoinColumn(name="designation_id")
	private Designation designation;
	
	@Column(name="appointment_date")
	private Date appointmentDate;
	
	@Column(name="share")
	private String share;
	
	@ManyToOne
	@JoinColumn(name="gender_code", referencedColumnName = "gender_code")
	private Gender gender;
	
	@ManyToOne
	@JoinColumn(name="caste_code", referencedColumnName = "caste_code")
	private Caste caste;
	
	@ManyToOne
	@JoinColumn(name="fpo_id")
	private Fpo fpoId;
	
	@Column(name="is_active")
	private boolean isActive;
	
}
