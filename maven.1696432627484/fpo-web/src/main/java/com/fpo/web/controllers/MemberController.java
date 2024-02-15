package com.fpo.web.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aashdit.framework.core.ServiceOutcome;
import com.fpo.web.services.CommonService;
import com.fpo.web.services.MemberService;
import com.fpo.web.utils.ApplicationConstants;
import com.fpo.web.vos.FarmerDtls;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member")
@Slf4j
public class MemberController {

	@Autowired private CommonService commonService;
	
	@Autowired private MemberService memberService;
	
	@GetMapping
	public String addMember(Model model, @RequestParam(value="memberCode", required = false) String memberCode) {
		try {
			model.addAttribute("genderList", commonService.getAllData(ApplicationConstants.GENDER).getData());
			model.addAttribute("casteList", commonService.getAllData(ApplicationConstants.CASTE ).getData());
			if(Optional.ofNullable(memberCode).isPresent()) {
				ServiceOutcome<FarmerDtls> byMemberCode = memberService.getByMemberCode(memberCode);
				model.addAttribute("memberDtls", byMemberCode.getData());
				System.out.println(byMemberCode.getData());
			}
		} catch (Exception e) {
			log.error("Exception occurred in addMember()-> MemberController "+e);
		}
		return "site.addMember";
	}
	
	@PostMapping("/manage-member")
	public String manageMember(FarmerDtls farmerDtls, RedirectAttributes attr) {
		try {
			ServiceOutcome<FarmerDtls> manageMember = memberService.manageMember(farmerDtls);
			attr.addFlashAttribute(manageMember.getOutcome()?"success_msg":"error_msg", manageMember.getOutcome()?manageMember.getMessage():manageMember.getMessage());
		} catch (Exception e) {
			log.error("Exception occurred in manageMember()-> MemberController "+e);
		}
		return "redirect:/member";
	}
	
	@GetMapping("/list")
	public String memberList(Model model) {
		try {
			model.addAttribute("memberList", commonService.getAllData(ApplicationConstants.MEMBER).getData());
		} catch (Exception e) {
			log.error("Exception occurred in memberList()-> MemberController "+e);
		}
		return "site.memberList";
	}
	
}
