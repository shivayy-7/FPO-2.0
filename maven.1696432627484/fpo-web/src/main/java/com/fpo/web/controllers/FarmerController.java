package com.fpo.web.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aashdit.framework.core.ServiceOutcome;
import com.aashdit.umt.model.User;
import com.aashdit.umt.util.SecurityHelper;
import com.fpo.web.services.CommonService;
import com.fpo.web.services.FarmerService;
import com.fpo.web.services.MemberService;
import com.fpo.web.utils.ApplicationConstants;
import com.fpo.web.utils.UserAdminstrativeUtil;
import com.fpo.web.vos.FarmerDtls;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/farmer")
@Slf4j
public class FarmerController {
	
	@Autowired private FarmerService farmerService;
	
	@Autowired private MemberService memberService;
	
	@Autowired private CommonService commonService;
	
	@Autowired private UserAdminstrativeUtil userAdminstrative;

	@GetMapping
	public String farmerPage(Model model, @RequestParam(value="memberCode" ,required = false) String memberCode) {
		User user = SecurityHelper.getCurrentUser();
		try {
			Long adminstrativeId = userAdminstrative.getAdminstrativeId(user);
			model.addAttribute("genderList", commonService.getAllData(ApplicationConstants.GENDER).getData());
			model.addAttribute("casteList", commonService.getAllData(ApplicationConstants.CASTE ).getData());
			ServiceOutcome<List<?>> gpList = commonService.getAdminstartiveNameByAdminstrativeId(null, adminstrativeId, "GP");
			model.addAttribute("gpList", gpList.getData());
			model.addAttribute("blk", adminstrativeId);
			model.addAttribute("district", commonService.getDistrictByAdminstrativeId(adminstrativeId).getData());
			model.addAttribute("componentList", commonService.getAllData(ApplicationConstants.COMPONENT).getData());
			model.addAttribute("distList", commonService.getAllData(ApplicationConstants.DISTRICT).getData());
			model.addAttribute("blockList", commonService.getAllData(ApplicationConstants.BLOCK).getData());
			model.addAttribute("bankList", commonService.getAllData(ApplicationConstants.BANK).getData());
			model.addAttribute("memberList", commonService.getAllData(ApplicationConstants.MEMBER).getData());
			if(Optional.ofNullable(memberCode).isPresent()) {
				ServiceOutcome<FarmerDtls> byMemberCode = memberService.getByMemberCode(memberCode);
				model.addAttribute("memberDtls", byMemberCode.getData());
			}
		} catch (Exception e) {
		   log.error("Exception occurred in farmerPage()-> FarmerController "+ e);	
		}
		return "site.addFarmer";
	}
	
	@GetMapping("/member-to-farmer")
	public String memberToFarmer(Model model) {
		try {
			
			model.addAttribute("memberList", commonService.getAllData(ApplicationConstants.MEMBER).getData());
		} catch (Exception e) {
		   log.error("Exception occurred in memberToFarmer()-> FarmerController "+ e);	
		}
		return "site.memberToFarmer";
	}
	
	@PostMapping("/manage-farmer")
	public String addNUpdateFarmer(FarmerDtls farmerDtls, RedirectAttributes attr) {
		User user = SecurityHelper.getCurrentUser();
//		ServiceOutcome<FarmerDtls> manageFarmer = new ServiceOutcome<>();
		try {
			System.out.println(farmerDtls);
			ServiceOutcome<FarmerDtls> manageFarmer = farmerService.manageFarmer(farmerDtls);
			attr.addFlashAttribute(manageFarmer.getOutcome()?"success_msg":"error_msg", manageFarmer.getOutcome()?manageFarmer.getMessage():manageFarmer.getMessage());
		} catch (Exception e) {
		   log.error("Exception occurred in addNUpdateFarmer()-> FarmerController "+ e);	
		}
		return "redirect:/farmer";
	}
	
}
