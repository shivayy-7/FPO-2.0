package com.fpo.web.entities;

import java.io.Serializable;

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
@Table(name="t_fpo_demography_map")
@Data
public class FpoDemographyMap extends Auditable<User> implements Serializable {

	private static final long serialVersionUID = -5640016908742012556L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="fpo_demography_id")
	private Long demographyId;
	
	@ManyToOne
	@JoinColumn(name="distId")
	private District distId;
	
	@ManyToOne
	@JoinColumn(name="blockId")
	private Block blockId;
	
	@ManyToOne
	@JoinColumn(name="gpId")
	private Grampanchayat gpId;
	
	@ManyToOne
	@JoinColumn(name="village_id")
	private Village villageId;
	
	@ManyToOne
	@JoinColumn(name="fpo_id")
	private Fpo fpoId;
	
	@Column(name="areas_covered")
	private String areasCovered;
	
	@Column(name="number_of_farms")
	private Long numberOfFarms;
	
	@Column(name="is_active")
	private boolean isActive;

}
