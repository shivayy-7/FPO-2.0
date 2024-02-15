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

@Entity
@Table(name = "t_mst_designation")
@Data
public class Designation extends Auditable<User> implements Serializable {
	
	private static final long serialVersionUID = 755946378766236024L;

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name="designation_id")
	 private Long designationId;
	 
	 @Column(name="designation_code")
	 private String designationCode;
	 
	 @Column(name="designation_name")
	 private String designationName;
	 
	 @Column(name="is_active")
	 private boolean isActive;
}
