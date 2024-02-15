package com.fpo.web.entities;

import com.aashdit.framework.core.annotation.Sortable;
import com.aashdit.framework.core.model.Auditable;
import com.aashdit.umt.model.User;
import org.springframework.context.i18n.LocaleContextHolder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Locale;

@Entity
@Table(name = "t_mst_district", schema = "public")
public class District extends Auditable<User> implements Serializable {

	private static final long serialVersionUID = 285701719160134651L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "district_id")
	private Long districtId;

	@Column(name = "district_code")
	private String districtCode;

	@Column(name = "district_name_en")
	@Sortable(lang = "en")
	private String districtNameEN;

	@Column(name = "district_name_hi")
	@Sortable(lang = "hi")
	private String districtNameHI;

	@Transient
	private String districtName;

	@ManyToOne
	@JoinColumn(name = "state_id")
	private State state;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "district_lgd_code")
	@Sortable(lang = "lgd")
	private String districtLgdCode;
	
	@Column(name = "district_census_code")
	@Sortable(lang = "census")
	private String districtCensusCode;
	
	@Column(name = "district_tribal")
	private Boolean districtTribal;
	
	public String getDistrictLgdCode() {
		return districtLgdCode;
	}

	public void setDistrictLgdCode(String districtLgdCode) {
		this.districtLgdCode = districtLgdCode;
	}

	public String getDistrictCensusCode() {
		return districtCensusCode;
	}

	public void setDistrictCensusCode(String districtCensusCode) {
		this.districtCensusCode = districtCensusCode;
	}



	public Boolean getDistrictTribal() {
		return districtTribal;
	}

	public void setDistrictTribal(Boolean districtTribal) {
		this.districtTribal = districtTribal;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getDistrictNameEN() {
		return districtNameEN;
	}

	public void setDistrictNameEN(String districtNameEN) {
		this.districtNameEN = districtNameEN;
	}

	public String getDistrictNameHI() {
		return districtNameHI;
	}

	public void setDistrictNameHI(String districtNameHI) {
		this.districtNameHI = districtNameHI;
	}

	public String getDistrictName() {
		Locale locale = LocaleContextHolder.getLocale();
		// log.debug(locale.getLanguage());
		switch (locale.getLanguage()) {
		case "en":
			return this.getDistrictNameEN();
		case "hi":
			return this.getDistrictNameHI();
		default:
			return this.getDistrictNameEN();
		}
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
