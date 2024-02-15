package com.fpo.web.entities;

import java.io.Serializable;

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
@Data
@Table(name="t_training_fpo_map")
public class TrainingFpoMap extends Auditable<User> implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 6682520972222605902L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "training_fpo_id", nullable = false)
	private Long trainingFpoId;
	
	@ManyToOne
	@JoinColumn(name="fpo_id")
	private Fpo fpoId;
	
	@ManyToOne
	@JoinColumn(name="training_id")
	private Training trainingId;
	
	@Column(name = "is_active")
	private boolean isActive;
	
//	@Column(name = "training_acceptance")
//    private Boolean trainingAcceptance;
	

}
