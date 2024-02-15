package com.fpo.web.entities;

import com.aashdit.framework.core.annotation.Sortable;
import com.aashdit.framework.core.model.Auditable;
import com.aashdit.umt.model.User;
import org.springframework.context.i18n.LocaleContextHolder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Locale;

@Entity
@Table(name = "t_mst_muncipality", schema = "public")
public class Municipality extends Auditable<User> implements Serializable {

	private static final long serialVersionUID = -4103610485238395024L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "municipality_id")
	private Long municipalityId;

	@Column(name = "municipality_name_en")
	@Sortable(lang = "en")
	private String municipalityNameEn;

	@Column(name = "municipality_name_hi")
	@Sortable(lang = "hi")
	private String municipalityNameHi;

	@Transient
	private String municipalityName;

	@Column(name = "municipality_code")
	private String municipalityCode;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "muni_lgd_code")
	@Sortable(lang = "lgd")
	private String muniLgdCode;
	
	public String getMuniLgdCode() {
		return muniLgdCode;
	}

	public void setMuniLgdCode(String muniLgdCode) {
		this.muniLgdCode = muniLgdCode;
	}

	public String getMuniCensusCode() {
		return muniCensusCode;
	}

	public void setMuniCensusCode(String muniCensusCode) {
		this.muniCensusCode = muniCensusCode;
	}

	public String getMuniTribal() {
		return muniTribal;
	}

	public void setMuniTribal(String muniTribal) {
		this.muniTribal = muniTribal;
	}

	@Column(name = "muni_census_code")
	@Sortable(lang = "census")
	private String muniCensusCode;
	
	@Column(name = "muni_tribal")
	private String muniTribal;
	
	@ManyToOne
	@JoinColumn(name = "district_id")
	private District district;

	public Long getMunicipalityId() {
		return municipalityId;
	}

	public void setMunicipalityId(Long municipalityId) {
		this.municipalityId = municipalityId;
	}

	public String getMunicipalityNameEn() {
		return municipalityNameEn;
	}

	public void setMunicipalityNameEn(String municipalityNameEn) {
		this.municipalityNameEn = municipalityNameEn;
	}

	public String getMunicipalityNameHi() {
		return municipalityNameHi;
	}

	public void setMunicipalityNameHi(String municipalityNameHi) {
		this.municipalityNameHi = municipalityNameHi;
	}

	public String getMunicipalityName() {
		Locale locale = LocaleContextHolder.getLocale();
		switch (locale.getLanguage()) {
		case "en":
			return this.getMunicipalityNameEn();
		case "hi":
			return this.getMunicipalityNameHi();
		default:
			return this.getMunicipalityNameEn();
		}
	}

	public void setMunicipalityName(String municipalityName) {
		this.municipalityName = municipalityName;
	}

	public String getMunicipalityCode() {
		return municipalityCode;
	}

	public void setMunicipalityCode(String municipalityCode) {
		this.municipalityCode = municipalityCode;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

}
