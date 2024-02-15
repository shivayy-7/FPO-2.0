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
@Data
@Table(name="t_frm_member")
public class Member extends Auditable<User> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7198854309012196728L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="member_id")
	private Long memberId;
	
	@Column(name="member_code")
	private String memberCode;
	
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
	
	@Column(name="total_area")
	private String totalArea;
	
	@Column(name="payment_amnt")
	private String paymentAmnt;
	
	@Column(name="payment_mode")
	private String paymentMode;
	
	@Column(name="is_active")
	private Boolean isActive;
	
	@ManyToOne
	@JoinColumn(name="frm_id")
	private Farmer farmer;
	
	@Column(name="date_of_reg")
	private Date dateOfReg;
	
}
