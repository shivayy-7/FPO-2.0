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
import com.aashdit.umt.model.User;
import com.aashdit.umt.util.SecurityHelper;
import com.fpo.web.services.CommonService;
import com.fpo.web.services.FarmService;
import com.fpo.web.utils.UserAdminstrativeUtil;
import com.fpo.web.vos.FarmDtls;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/farm")
public class FarmController {
	
	@Autowired private CommonService commonService;
	
	@Autowired private UserAdminstrativeUtil userAdminstrative;
	
	@Autowired private FarmService farmService;;
	
	@GetMapping
	public String addFarm(Model model, @RequestParam(value="farmCode", required = false) String farmCode) {
		User user = SecurityHelper.getCurrentUser();
		try {
			List<Long> allAdminstrativeId = userAdminstrative.getAllAdminstrativeId(user);
			ServiceOutcome<List<?>> blockNameList = commonService.getAdminstartiveNameByAdminstrativeId(allAdminstrativeId ,null, "BLOCK");
			model.addAttribute("blockList", blockNameList.getData());
			if(Optional.ofNullable(farmCode).isPresent()) {
				ServiceOutcome<FarmDtls> farmByFarmCode = farmService.getFarmByFarmCode(farmCode);
                model.addAttribute("farmCodeDtls", farmByFarmCode.getData());		
			}
		} catch (Exception e) {
			log.error("Exception occurred in addFarm()-> FarmController "+ e.getMessage());
		}
		return "site.addFarm";
	}
	
	@PostMapping("manageFarm")
	public String addNUpdate(FarmDtls farmDtls, RedirectAttributes attr) {
		User user = SecurityHelper.getCurrentUser();
		try {
			ServiceOutcome<FarmDtls> manageFarm = farmService.manageFarm(farmDtls);
			attr.addFlashAttribute(manageFarm.getOutcome()?"success_msg":"error_msg", manageFarm.getOutcome()?manageFarm.getMessage():manageFarm.getMessage());
			if(Optional.ofNullable(farmDtls.getFarmVO().getFrmId()).isPresent()) {
				return"redirect:/farm/list";
			}
		} catch (Exception e) {
			log.error("Exception occurred in addNUpdate()-> FarmController "+ e.getMessage());
		}
		return "redirect:/farm";
	}

	@GetMapping("/list")
	public String farmList(Model model) {
		try {
			User user = SecurityHelper.getCurrentUser();
			List<Long> allAdminstrativeId = userAdminstrative.getAllAdminstrativeId(user);
			ServiceOutcome<List<?>> blockNameList = commonService.getAdminstartiveNameByAdminstrativeId(allAdminstrativeId ,null, "BLOCK");
			model.addAttribute("blockList", blockNameList.getData());
			if (model.containsAttribute("farmData")) {
	            model.addAttribute("farmData", model.getAttribute("farmData"));
	            model.addAttribute("farmDtls", model.getAttribute("farmDtls"));
	        }
		} catch (Exception e) {
			log.error("Exception occurred in farmList()-> FarmController "+ e.getMessage());
		}
		return "site.farmList";
	}
	
	@GetMapping("/getFarm")
	public String getFarm(FarmDtls farmDtls, RedirectAttributes attr, Model model) {
		try {
			ServiceOutcome<FarmDtls> farmData = farmService.getFarmData(farmDtls);
			attr.addFlashAttribute("farmData", farmData.getData());
			attr.addFlashAttribute("farmDtls", farmDtls);
		} catch (Exception e) {
			log.error("Exception occurred in getFarm()-> FarmController "+ e.getMessage());
		}
		return "redirect:/farm/list";
	}
}
