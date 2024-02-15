package com.fpo.web.vos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fpo.web.entities.Caste;
import com.fpo.web.entities.Designation;
import com.fpo.web.entities.Fpo;
import com.fpo.web.entities.Gender;

import lombok.Data;

@Data
public class FpoManagementVo {

	private Long directorId;
	private String name;
	private String din;
	private Designation designation;
	private String appointmentDate;
	private String share;
	private Gender gender;
	private Caste caste;
	private Fpo fpoId;
	private boolean isActive;
	
}
