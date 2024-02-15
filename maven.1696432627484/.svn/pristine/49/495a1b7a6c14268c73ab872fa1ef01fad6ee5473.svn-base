package com.fpo.web.entities;

import com.aashdit.framework.core.model.Auditable;
import com.aashdit.umt.model.User;

import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "t_mst_bank_branch_master")
public class BankBranch extends Auditable<User> implements Serializable {
	private static final long serialVersionUID = -384517585858702L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bank_branch_id")
	private Long bankBranchId;

	@NotNull
	@Column(name = "bank_name")
	private String bankName;

	@NotNull
	@Column(name = "ifsc")
	private String ifsc;

	@NotNull
	@Column(name = "branch_code")
	private String branchCode;

	@NotNull
	@Column(name = "branch_name")
	private String branchName;

	@NotNull
	@Column(name = "address")
	private String address;

	@Column(name = "is_active")
	private Boolean isActive;

	public Long getBankBranchId() {
		return bankBranchId;
	}

	public void setBankBranchId(Long bankBranchId) {
		this.bankBranchId = bankBranchId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}