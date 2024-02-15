package com.fpo.web.entities;

import com.aashdit.framework.core.annotation.Sortable;
import com.aashdit.framework.core.model.Auditable;
import com.aashdit.umt.model.User;
import org.springframework.context.i18n.LocaleContextHolder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Locale;

@Entity
@Table(name = "t_mst_state", schema = "public")
public class State extends Auditable<User> implements Serializable {
	private static final long serialVersionUID = 285701719160134651L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "state_id")
	private Long stateId;

	@Column(name = "state_name_en")
	@Sortable(lang = "en")
	private String stateNameEN;

	@Column(name = "state_name_hi")
	@Sortable(lang = "hi")
	private String stateNameHI;

	@Transient
	private String stateName;

	@Column(name = "state_code")
	private String stateCode;

	@Column(name = "is_active")
	private Boolean isActive;

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		Locale locale = LocaleContextHolder.getLocale();
		switch (locale.getLanguage()) {
		case "en":
			return this.getStateNameEN();
		case "hi":
			return this.getStateNameHI();
		default:
			return this.getStateNameEN();
		}
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getStateNameEN() {
		return stateNameEN;
	}

	public void setStateNameEN(String stateNameEN) {
		this.stateNameEN = stateNameEN;
	}

	public String getStateNameHI() {
		return stateNameHI;
	}

	public void setStateNameHI(String stateNameHI) {
		this.stateNameHI = stateNameHI;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getStateCode() {
		return stateCode;
	}

}
