package com.fpo.web.entities;

import java.io.Serializable;

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
@Table(name="t_frm_farm")
public class Farm extends Auditable<User> implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="farm_id")
	private Long farmId;

	@Column(name="farm_code")
	private String farmCode;

	@Column(name="land_area_of_farm")
	private String landAreaOfFarm;
	
	@Column(name="plotNo")
	private String plotNo;
	
	@Column(name="khata_no")
	private String khataNo;
	
	@Column(name="crop_name")
	private String cropName;
	
	@Column(name="crop_pattern")
	private String cropPattern;
	
	@Column(name="prodQuantity")
	private String prodQuantity;
	
	@Column(name="pesticide")
	private String pesticide;
	
	@Column(name="is_soil_analysis")
	private Boolean isSoilAnalysis;
	
	@Column(name="total_investment")
	private String totalInvestment;
	
	@Column(name="total_return")
	private String totalReturn;
	
	@Column(name="irrigation_facility")
	private String irrigationFacility;
	
	@Column(name="plantation_month")
	private String plantationMonth;
	
	@Column(name="harvesting_month")
	private String harvestingMonth;
	
	@Column(name="energy_usage")
	private String energyUsage;
	
	@Column(name="is_crop_rotation")
	private Boolean isCropRotation;
	
	@Column(name="is_active")
	private Boolean isActive;
	
	@ManyToOne
	@JoinColumn(name="farmer_id")
	private Farmer farmerId;
	
	@Column(name="rotation_crop_name")
	private String rotationCropName;
	
	
}
