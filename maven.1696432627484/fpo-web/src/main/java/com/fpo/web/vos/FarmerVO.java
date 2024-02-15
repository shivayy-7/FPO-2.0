package com.fpo.web.vos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fpo.web.entities.BankBranch;
import com.fpo.web.entities.Caste;
import com.fpo.web.entities.Gender;
import com.fpo.web.entities.Village;

import lombok.Data;

@Data
public class FarmerVO {
	
	private Long farmerId;
	private String farmerCode;
	private String name;
	private String aadharNo;
	private Gender gender;
	private Caste caste;
	private String dob;
	private Long noOfFamilyMembers;
	private String mobile;
	private String figName;
	private String frmType;
	private String annualIncome;
	private Long depenPerson;
	private String address;
	private Village villageId;
	private BankBranch bankBranchId;
	private String accountNo;
	private String accType;
	private Boolean isHealthInsurance;
	private Boolean isJanshreeBimaYojana;
	private Boolean isPensionCoverage;
	private Boolean eduBenifits;
	private Boolean isActive;
	private String status;

}