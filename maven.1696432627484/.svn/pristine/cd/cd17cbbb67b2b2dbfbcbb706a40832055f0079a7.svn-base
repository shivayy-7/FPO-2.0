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
@Table(name = "t_mst_subdivision")
@Data
public class Subdivision extends Auditable<User> implements Serializable {

	private static final long serialVersionUID = -4017365926398370178L;

	@Id
	@Column(name = "subdivision_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long subdivisionId;

	@Column(name = "subdivision_name")
	private String subdivisionName;

	@ManyToOne
	@JoinColumn(name = "district_id")
	private District district;

	@Column(name = "remarks")
	private String remarks;
	
	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "subdivision_code")
	private String subdivisionCode;

}
	