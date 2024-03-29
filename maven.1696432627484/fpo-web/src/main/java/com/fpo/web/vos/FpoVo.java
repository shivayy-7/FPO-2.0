package com.fpo.web.vos;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;

import com.aashdit.umt.model.User;
import com.fpo.web.entities.Block;
import com.fpo.web.entities.Scheme;

import lombok.Data;

@Data
public class FpoVo {
	
	private Long fpoId;
	private String fpoCode;
	private String fpoName;
	private String component;
	private String activity;
	private List<String> subActivity;
	private String dateOfReg;
	private Long noOfFrm;
	private String dateOfIncorporation;
	private String cin;
	private String companyPan;
	private String companyCategory;
	private String ageOfCompany;
	private Long noOfDirector;
	private String sharedCapital;
	private String regAddress;
	private String buisnessAddress;
	private String email;
	private String mobileOfAuthSignatory;
	private String authSignatory;
	private String totalInvestment;
	private String debt;
	private String promoterHolding;
	private String totalLoanAmt;
	private String loanRepaid;
	private String roi;
	private String totalOutstanAmt;
	private boolean isActive;
	private String status;
	private User userId;
	
	

}
