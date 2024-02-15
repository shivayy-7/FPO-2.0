package com.fpo.web.vos;

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
@Data
@Table(name="t_mst_document")
public class DocumentVO extends Auditable<User> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8462613869764516828L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="doc_id")
	private Long docId;
	
	@Column(name="doc_name")
	private String docName;
	
	@Column(name="doc_code")
	private String docCode;
	
	@Column(name="is_active")
	private boolean isActive;
	

}
