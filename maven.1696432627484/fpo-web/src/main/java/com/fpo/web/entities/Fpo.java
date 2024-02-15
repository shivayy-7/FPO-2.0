package com.fpo.web.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.aashdit.framework.core.model.Auditable;
import com.aashdit.umt.model.User;

import lombok.Data;

@Entity
@Table(name="t_frm_fpo")
@Data
public class Fpo extends Auditable<User> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5640016908742012556L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="fpo_id")
	private Long fpoId;
	
	@Column(name="fpo_code")
	private String fpoCode;
	
	@Column(name="fpo_name")
	private String fpoName;
	
	@Column(name="date_of_reg")
	private Date dateOfReg;
	
	@Column(name="no_of_frm")
	private Long noOfFrm;
	
	@Column(name="date_of_incorporation")
	private Date dateOfIncorporation;
	
	@Column(name="cin")
	private String cin;
	
	@Column(name="company_pan")
	private String companyPan;
	
	@Column(name="company_category")
	private String companyCategory;
	
	@Column(name="age_of_company")
	private String ageOfCompany;
	
	@Column(name="no_of_director")
	private Long noOfDirector;
	
	@Column(name="sharedCapital")
	private String sharedCapital;
	
	@Column(name="reg_address")
	private String regAddress;
	
	@Column(name="buisness_address")
	private String buisnessAddress;
	
	@Column(name="email")
	private String email;
	
	@Column(name="mobile_of_auth_signatory")
	private String mobileOfAuthSignatory;
	
	@Column(name="auth_signatory")
	private String authSignatory;
	
	@Column(name="total_investment")
	private String totalInvestment;
	
	@Column(name="debt")
	private String debt;
	
	@Column(name="promoter_holding")
	private String promoterHolding;
	
	@Column(name="total_loan_amt")
	private String totalLoanAmt;
	
	@Column(name="loan_repaid")
	private String loanRepaid;
	
	@Column(name="roi")
	private String roi;
	
	@Column(name="total_outstan_amt")
	private String totalOutstanAmt;
	
	@Column(name="is_active")
	private boolean isActive;
	
	@Column(name="status")
	private String status;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User userId;
	
	
}
