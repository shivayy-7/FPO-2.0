package com.fpo.web.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name="t_fpo_subactivity_map")
@Data
public class FpoSubactivityMap extends Auditable<User> implements Serializable {

	private static final long serialVersionUID = -5640016908742012556L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="fpo_subact_id")
	private Long fpoSubactivityId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fpo_id")
	private Fpo fpo;
	
	@ManyToOne
	@JoinColumn(name="sub_act_id")
	private SubActivity subActivityId;
	
	@Column(name="is_active")
	private boolean isActive;
	
}