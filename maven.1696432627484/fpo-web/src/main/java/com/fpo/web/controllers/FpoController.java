package com.fpo.web.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.tiles.request.ApplicationAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aashdit.framework.core.ServiceOutcome;
import com.aashdit.umt.model.User;
import com.aashdit.umt.util.SecurityHelper;
import com.fpo.web.services.CommonService;
import com.fpo.web.services.FpoService;
import com.fpo.web.utils.ApplicationConstants;
import com.fpo.web.utils.UserAdminstrativeUtil;
import com.fpo.web.vos.FpoDtls;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/fpo")
public class FpoController {

	@Autowired
	private FpoService fpoService;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired private UserAdminstrativeUtil userAdminstrative;
	
	
	@GetMapping
	public String addFpo(Model model,@RequestParam(value="fpoCode", required = false) String fpoCode) {
		User user = SecurityHelper.getCurrentUser();
		try {
			List<Long> allAdminstrativeId = userAdminstrative.getAllAdminstrativeId(user);
			ServiceOutcome<List<?>> blockNameList = commonService.getAdminstartiveNameByAdminstrativeId(allAdminstrativeId ,null, "BLOCK");
			model.addAttribute("blockList", blockNameList.getData());
			model.addAttribute("casteList", commonService.getAllData(ApplicationConstants.CASTE ).getData());
			model.addAttribute("distList", commonService.getAllData(ApplicationConstants.DISTRICT).getData());
			model.addAttribute("componentList", commonService.getAllData(ApplicationConstants.COMPONENT).getData());
			model.addAttribute("genderList", commonService.getAllData(ApplicationConstants.GENDER).getData());
			model.addAttribute("designationList", commonService.getAllData(ApplicationConstants.DESIGNATION).getData());
			model.addAttribute("schemeList", commonService.getAllData(ApplicationConstants.SCHEME).getData());
			if(fpoCode!=null) {
				ServiceOutcome<FpoDtls> fpoByCode = fpoService.getFpoByCode(fpoCode);
				model.addAttribute("fpoDtls", fpoByCode.getData());
				System.out.println(fpoByCode.getData());
				model.addAttribute("status", "UPDATE");
			}
			
		} catch (Exception e) {
			log.error("Exception occurred in addFpo()-> FpoController "+ e.getMessage());
		}
		return"site.addFpo";
	}
	
	@PostMapping("/addNUpdate")
	public String addNUpdateFpo(FpoDtls fpoDtls, RedirectAttributes attr) {
		try {
			System.out.println(fpoDtls);
			ServiceOutcome<Boolean> saveFpoDtls = fpoService.saveFpoDtls(fpoDtls);
			attr.addFlashAttribute(saveFpoDtls.getOutcome()?"success_msg":"error_msg", saveFpoDtls.getOutcome()?saveFpoDtls.getMessage():saveFpoDtls.getMessage());
		} catch (Exception e) {
			log.error("Exception occurred in addNUpdateFpo()-> FpoController "+ e.getMessage());
		}
		return "redirect:/fpo";
	}
	
	@GetMapping("/list")
	public String fpoList(Model model,  RedirectAttributes attr) {
		try {
				model.addAttribute("fpoList", commonService.getAllData("FPO").getData());
		} catch (Exception e) {
			log.error("Exception occurred in fpoList()-> FpoController "+ e.getMessage());
		}
		return"site.fpoList";
	}
	
	// FPO training controller
	
	@GetMapping("/event-Acceptance")
	public String eventAcceptance(Model model, @RequestParam(name="eventCode", required = false) String eventCode, @RequestParam(name="status", required = false) String status, RedirectAttributes attr) {
		try {
			User user = SecurityHelper.getCurrentUser();
			if(Optional.ofNullable(eventCode).isPresent()) {
				ServiceOutcome<?> setEventAcceptanceValue = fpoService.setEventAcceptanceValue(eventCode, status);
				model.addAttribute("status", status);
				model.addAttribute(setEventAcceptanceValue.getOutcome()?"success_msg":"error_msg", setEventAcceptanceValue.getOutcome()?setEventAcceptanceValue.getMessage():setEventAcceptanceValue.getMessage());
				model.addAttribute("eventAcceptanceValue", setEventAcceptanceValue.getData());
			}
				
				ServiceOutcome<FpoDtls> eventAvailable = fpoService.isEventAvailable(user);
				model.addAttribute("eventAvailable", eventAvailable.getData());
			
			
		} catch (Exception e) {
			log.error("Exception occurred in eventAcceptance()-> FpoController "+ e.getMessage());
		}
		return"site.fpoList";
	}
	
}