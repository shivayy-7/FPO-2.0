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
@Table(name = "t_mst_village")
@Data
public class Village extends Auditable<User> implements Serializable {

	private static final long serialVersionUID = -4103610485238395024L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "village_id")
	private Long villageId;

	@Column(name = "village_name_en")
	@Sortable(lang = "en")
	private String villageNameEn;

	@Column(name = "village_name_hi")
	@Sortable(lang = "hi")
	private String villageNameHi;

	@Transient
	private String villageName;

	@Column(name = "village_code")
	private String villageCode;

	@ManyToOne
	@JoinColumn(name = "gp_id")
	private Grampanchayat gpId;

	@Column(name = "village_remarks")
	private String villageRemarks;

	@Column(name = "is_active")
	private Boolean isActive;

//	@Column(name = "village_lgd_code")
//	@Sortable(lang = "lgd")
//	private String villageLgdCode;
//
//
//	@Column(name = "village_census_code")
//	@Sortable(lang = "census")
//	private String villageCensusCode;
//	
//	@Column(name = "village_tribal")
//	private Boolean villageTribal;
//	
//	@Transient
//	private Boolean isSelected = false;
//
//	@Transient
//	private String aapCode="";

}