package com.fpo.web.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
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
@Table(name="t_fpo_scheme_mngmnt")
@Data
public class FpoSchemeMap extends Auditable<User> implements Serializable {

	private static final long serialVersionUID = -5640016908742012556L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="scheme_mngmnt_id")
	private Long schemeMngmntId;
	
	@ManyToOne
	@JoinColumn(name="scheme_name", referencedColumnName = "scheme_code")
	private Scheme schemee;
	
	@Column(name="type")
	private String type;
	
	@Column(name="date_of_participation")
	private Date dateOfParticipation;
	
	@ManyToOne
	@JoinColumn(name="fpo_id")
	private Fpo fpoId;
	
	@Column(name="is_active")
	private boolean isActive;

}
