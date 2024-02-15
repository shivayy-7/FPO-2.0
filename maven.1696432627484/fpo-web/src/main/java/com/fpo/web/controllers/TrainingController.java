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
import com.fpo.web.services.TrainingService;
import com.fpo.web.utils.ApplicationConstants;
import com.fpo.web.vos.CbboVO;
import com.fpo.web.vos.FarmerCbboMngmtVO;
import com.fpo.web.vos.FpoDtls;
import com.fpo.web.vos.TrainingDtls;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/training")
@Slf4j
public class TrainingController {
	
	@Autowired private CommonService commonService;
	
	@Autowired private TrainingService trainingService;
	
	@GetMapping
	public String fpoTraining(Model model, @RequestParam(name="trainingCode", required = false) String trainingCode) {
		try {
			model.addAttribute("fpoList", commonService.getAllData(ApplicationConstants.FPO).getData()) ;
			if(Optional.ofNullable(trainingCode).isPresent()) {
				ServiceOutcome<TrainingDtls> trainingByTrainingCode = trainingService.getTrainingByTrainingCode(trainingCode);
				model.addAttribute("trainingDtls", trainingByTrainingCode.getData());
			}
		} catch (Exception e) {
			log.error("Exception occurred in fpoTraining()-> TrainingController "+ e.getMessage());
		}
//		return "site.training";
//		return "site.myerror";
		return "site.400";
	}
	
	@PostMapping("/manageTraining")
	public String manageTraining(TrainingDtls trainingDtls, RedirectAttributes attr) {
		
		try {
			ServiceOutcome<TrainingDtls> addNUpdateTraining = trainingService.addNUpdateTraining(trainingDtls);
			attr.addFlashAttribute(addNUpdateTraining.getOutcome()?"success_msg":"error_msg", addNUpdateTraining.getOutcome()?addNUpdateTraining.getMessage():addNUpdateTraining.getMessage());
		} catch (Exception e) {
			log.error("Exception occurred in manageTraining()-> TrainingController "+ e.getMessage());
		}
		return "redirect:/training";
		
	}
	
	@GetMapping("/info")
	public String trainingInfo(Model model) {
		try {
			
			ServiceOutcome<TrainingDtls> searchTrainingInfo = trainingService.searchTrainingInfo(null, null, ApplicationConstants.UPCOMING);
			model.addAttribute("trainingInfo", searchTrainingInfo.getData());
		} catch (Exception e) {
			log.error("Exception occurred in trainingInfo()-> TrainingController "+ e.getMessage());
		}
		return "site.trainingInfo";
	}
	
	@GetMapping("/searchInfo")
	public String searchInfo(String fromdateTodate, String trainingType, String status,Model model) {
		try {
			if(!Optional.ofNullable(status).isPresent() || status.isEmpty()) {
				status = ApplicationConstants.UPCOMING;
			}
			ServiceOutcome<TrainingDtls> searchTrainingInfo = trainingService.searchTrainingInfo(fromdateTodate, trainingType, status);
			model.addAttribute("trainingInfo", searchTrainingInfo.getData());
			model.addAttribute("fromdateTodate", fromdateTodate);
			model.addAttribute("trainingType", trainingType);
			model.addAttribute("status", status);
		} catch (Exception e) {
			log.error("Exception occurred in searchInfo()-> TrainingController "+ e.getMessage());
		}
		return "site.trainingInfo";
	}
	
	

}
