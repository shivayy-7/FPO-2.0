package com.fpo.web.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name="t_frm_farmer")
@Data
public class Farmer extends Auditable<User> implements Serializable{
	
	private static final long serialVersionUID = 3771027609349042604L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="farmer_id")
	private Long farmerId;
	
	@Column(name="farmer_code")
	private String farmerCode;
	
	@Column(name="name")
	private String name;
	
	@Column(name="aadhar_no")
	private String aadharNo;
	
	@ManyToOne
	@JoinColumn(name="gender_code", referencedColumnName = "gender_code")
	private Gender gender;
	
	@ManyToOne
	@JoinColumn(name="caste_code", referencedColumnName = "caste_code")
	private Caste caste;
	
	@Column(name="dob")
	private Date dob;
	
	@Column(name="no_of_family_members")
	private Long noOfFamilyMembers;
	
	@Column(name="mobile")
	private String mobile;
	
	@Column(name="fig_name")
	private String figName;
	
	@Column(name="frm_type")
	private String frmType;
	
	@Column(name="annual_income")
	private String annualIncome;
	
	@Column(name="depen_person")
	private Long depenPerson;
	
	@Column(name="address")
	private String address;
	
	@ManyToOne
	@JoinColumn(name="village_id")
	private Village villageId;
	
	@ManyToOne
	@JoinColumn(name="bank_branch_id")
	private BankBranch bankBranchId;
	
	@Column(name="account_no")
	private String accountNo;
	
	@Column(name="acc_type")
	private String accType;
	
	@Column(name="is_health_insurance")
	private boolean isHealthInsurance;
	
	@Column(name="is_janshree_bima_yojana")
	private boolean isJanshreeBimaYojana;
	
	@Column(name="is_pension_coverage")
	private boolean isPensionCoverage;
	
	@Column(name="edu_benifits")
	private boolean eduBenifits;
	
	@Column(name="is_active")
	private boolean isActive;
	
	@Column(name="status")
	private String status;
	
}
