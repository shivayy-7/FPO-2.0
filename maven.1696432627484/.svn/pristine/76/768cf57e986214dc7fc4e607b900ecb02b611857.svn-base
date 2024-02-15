package com.fpo.web.entities;

import com.aashdit.framework.core.model.Auditable;
import com.aashdit.umt.model.User;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "t_mst_caste")
@Data
public class Caste extends Auditable<User> implements Serializable {
	private static final long serialVersionUID = -717126664705573L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "caste_id")
	private Long casteId;

	@NotNull
	@Column(name = "is_active")
	private Boolean isActive;

	@NotNull
	@Column(name = "caste_code")
	private String casteCode;

	@NotNull
	@Column(name = "caste_name")
	private String casteName;

	@NotNull
	@Column(name = "caste_description")
	private String casteDescription;


}