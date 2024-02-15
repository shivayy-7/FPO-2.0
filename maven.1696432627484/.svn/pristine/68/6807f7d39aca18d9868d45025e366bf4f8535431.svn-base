package com.fpo.web.entities;

import java.io.Serializable;

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
@Data
@Table(name="t_farmer_documents")
public class FarmerDocument extends Auditable<User> implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 4429847437614884831L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="farmer_doc_id")
	private Long farmerDocId;
	
	@ManyToOne
	@JoinColumn(name="doc_id")
	private Document docId;
	
	@Column(name="module_code")
	private String moduleCode;
	
	@Column(name="sub_module_code")
	private String subModuleCode;
	
	@Column(name="path")
	private String path;
	
	@Column(name="is_active")
	private boolean isActive;
	
	@ManyToOne
	@JoinColumn(name="fpo_id")
	private Fpo fpoId;
	
	
}
