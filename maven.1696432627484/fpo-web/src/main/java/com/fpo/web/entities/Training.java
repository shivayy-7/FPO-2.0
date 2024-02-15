package com.fpo.web.entities;

import java.io.Serializable;
import java.util.Date;

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

@Data
@Entity
@Table(name = "t_frm_training_info")
public class Training extends Auditable<User> implements Serializable {

	private static final long serialVersionUID = 58722067099870908L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "training_id", nullable = false)
    private Long trainingId;
	
	@Column(name = "training_code")
    private String trainingCode;
	
	@Column(name = "training_type")
    private String trainingType;
	
	@Column(name = "training_name")
    private String trainingName;
	
	@Column(name = "date_of_training")
    private Date dateOfTraining;
	
	@Column(name = "trainer_name")
    private String trainerName;
	
	@Column(name = "no_of_day")
    private Long noOfDay;
	
	@Column(name = "fig")
    private String fig;
	
	@Column(name = "no_of_farmers")
    private Long noOfFarmers;
	
	@Column(name = "is_active")
    private Boolean isActive;
	
//	@Column(name = "subject")
//    private String subject;
//	
	@Column(name = "status")
    private String status;
	
}
