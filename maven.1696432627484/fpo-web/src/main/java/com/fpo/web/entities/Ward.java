package com.fpo.web.entities;

import com.aashdit.framework.core.model.Auditable;
import com.aashdit.umt.model.User;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_mst_ward", schema = "public")
public class Ward extends Auditable<User> implements Serializable {

	private static final long serialVersionUID = 5624156612372869552L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ward_id")
	private Long wardId;

	@Column(name = "ward_name")
	private String wardName;

	@Column(name = "ward_code")
	private String wardCode;

	@ManyToOne
	@JoinColumn(name ="municipality_id")
	private Municipality municipality;

	@Column(name = "is_active")
	private Boolean isActive;

	public Long getWardId() {
		return wardId;
	}

	public void setWardId(Long wardId) {
		this.wardId = wardId;
	}

	public String getWardName() {
		return wardName;
	}

	public void setWardName(String wardName) {
		this.wardName = wardName;
	}

	public String getWardCode() {
		return wardCode;
	}

	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}

	public Municipality getMunicipality() {
		return municipality;
	}

	public void setMunicipality(Municipality municipality) {
		this.municipality = municipality;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}
