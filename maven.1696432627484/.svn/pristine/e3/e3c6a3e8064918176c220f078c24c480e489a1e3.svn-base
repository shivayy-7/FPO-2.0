package com.fpo.web.entities;

import com.aashdit.framework.core.model.Auditable;
import com.aashdit.umt.model.User;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
//@Builder
//@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "t_mst_component")
@AllArgsConstructor
@NoArgsConstructor
public class Component extends Auditable<User> implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 851501109682112768L;


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cmp_id")
    private Long cmpId;


	@Column(name = "cmp_name_en")
	private String cmpNameEn;

    @Column(name = "cmp_name_hi")
    private String cmpNameHi;
	    
	@Column(name = "cmp_code")
	private String cmpCode;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "scheme_code", referencedColumnName = "scheme_code")
	private Scheme schemeCode;
	
	@Column(name = "cmp_description")
	private String cmpDescription;

	@Column(name = "cmp_duration")
	private String cmpDuration;
	
	@NotNull
	@Column(name = "is_active")
	private Boolean isActive;



}