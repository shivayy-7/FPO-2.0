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
@Table(name = "t_mst_block")
@Data
public class Block extends Auditable<User> implements Serializable {

	private static final long serialVersionUID = -4103610485238395024L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "block_id")
	private Long blockId;

	@Column(name = "block_code")
	private String blockCode;

	@Column(name = "block_name_en")
	@Sortable(lang = "en")
	private String blockNameEN;

	@Column(name = "block_name_hi")
	@Sortable(lang = "hi")
	private String blockNameHI;

	@Transient
	private String blockName;

	@ManyToOne
	@JoinColumn(name = "district_id")
	private District district;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "block_lgd_code")
	@Sortable(lang = "lgd")
	private String blockLgdCode;


	@Column(name = "block_census_code")
	@Sortable(lang = "census")
	private String blockCensusCode;
	
	@Column(name = "block_tribal")
	private Boolean blockTribal;
	
	@Transient
	private Boolean isSelected =false;

	@Transient
	private String aapCode="";


}
