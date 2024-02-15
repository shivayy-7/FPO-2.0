package com.fpo.web.vos;

import java.util.List;

import com.fpo.web.entities.Member;

import lombok.Data;

@Data
public class FarmerDtls {

	private FarmerVO farmerVO;
	private String component;
	private String activity;
	private List<String> subActivity;
	private Long gp;
	
	private FarmerSubActMapVO farmerSubActMapVO;
	
	private List<MemberVO> memberVO;
	private String dateOfReg;
	private MemberVO member;
	
}
