package com.fpo.web.controllers;

import com.aashdit.framework.core.ServiceOutcome;
import com.fpo.web.services.WorkFlowService;
import com.fpo.web.vos.WorkFlowHistoryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("fpo_workFlow")
public class WorkFlowController {
	
	@Autowired
	private WorkFlowService workFlowService;
	
	@PostMapping("/approvalCall")
	public String actionByAuthority(RedirectAttributes attr ,Long formId,String formEntity, String remarks, MultipartFile rejectionAtchmnt, String action) {
		try {
			ServiceOutcome<String> ss=workFlowService.actionByAutority(formId,formEntity,action,remarks,rejectionAtchmnt);
			attr.addFlashAttribute(ss.getData() != ""  ? "success_msg" : "error_msg", ss.getData());
		} catch (Exception e) {
			log.error("error in post  WorlflowContorller ==>"+e);	
			}
		return "redirect:/filter/pani-panchayat?filterPage="+formEntity;
	}
	
	@GetMapping("/getWorkFlowHistory")
	@ResponseBody
	public List<WorkFlowHistoryDto> getWorkFlowHistory(RedirectAttributes attr , Long formId, String moduleForm, @RequestParam(value="statusFinder",required=false) String statusFinder) {
		ServiceOutcome<List<WorkFlowHistoryDto>> ss= new ServiceOutcome<>();
		try {
			 ss=workFlowService.getWorkFlowByModule(formId,moduleForm,statusFinder);
		} catch (Exception e) {
			log.error("error in get WorlflowContorller ==>"+e);	
		}
		return ss.getData();
	}
	
}
