package com.fpo.web.entities;

import com.aashdit.framework.core.annotation.Sortable;
import com.aashdit.framework.core.model.Auditable;
import com.aashdit.umt.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.context.i18n.LocaleContextHolder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Locale;

@JsonIgnoreProperties(value= {"handler","hibernateLazyInitializer","FieldHandler"})
@Entity
@Table(name = "t_mst_gramapanchayat")
@Data
public class Grampanchayat extends Auditable<User> implements Serializable {

	private static final long serialVersionUID = 3736657245464159277L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gp_id")
	private Long gpId;

	@Column(name = "gp_code")
	private String gpCode;

	@Column(name = "gp_name_en")
	@Sortable(lang = "en")
	private String gpNameEN;

	@Column(name = "gp_name_hi")
	@Sortable(lang = "hi")
	private String gpNameHI;

	@Transient
	private String gpName;

	@ManyToOne
	@JoinColumn(name = "block_id")
	private Block block;

	@Column(name = "is_active")
	private Boolean isActive;

//	@Column(name = "gp_lgd_code")
//	@Sortable(lang = "lgd")
//	private String gpLgdCode;


//	@Column(name = "gp_census_code")
//	@Sortable(lang = "census")
//	private String gpCensusCode;
	
//	@Column(name = "gp_tribal")
//	private Boolean gpTribal;
	
//	@Transient
//	private String aapCode="";
//
//	@Transient
//	private Boolean isSelected = false;

}
