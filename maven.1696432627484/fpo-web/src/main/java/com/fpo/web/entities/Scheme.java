package com.fpo.web.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.aashdit.framework.core.model.Auditable;
import com.aashdit.umt.model.User;

import lombok.Data;

@Entity
@Table(name = "t_mst_scheme")
@Data
public class Scheme extends Auditable<User> implements Serializable {
	private static final long serialVersionUID = -725099211093037L;

	@Id
	@GeneratedValue(strategy  =  GenerationType.IDENTITY)
	@Column(name = "scheme_id")
	private Long schemeId;

	@Column(name = "scheme_name_en")
	private String schemeNameEn;

    @Column(name = "scheme_name_hi")
    private String schemeNameHi;
	    
	@Column(name = "scheme_code")
	private String schemeCode;

	@Column(name = "scheme_description")
	private String schemeDescription;

//	@NotNull
	@Column(name = "is_active")
	private Boolean isActive;


}