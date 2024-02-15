package com.fpo.web.controllers;

import com.aashdit.framework.core.ServiceOutcome;
import com.fpo.web.entities.*;
import com.fpo.web.services.InstallmentService;
import com.fpo.web.services.WorkFlowService;
import com.fpo.web.vos.MilestoneCollectorParentVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.fpo.web.utils.ApplicationConstants;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/installment")
public class InstallmentController {

    @Autowired
    private InstallmentService installmentService;

    @Autowired
    private WorkFlowService workFlowService;

    @GetMapping
    public String installmentProcess(Model model){
        try{
            List<FarmerInstallment> instList=installmentService.getAllInstallmentDetails();
            model.addAttribute("installment",instList);


            ServiceOutcome<List<StageForwardedRule>> buttons=workFlowService.getButtonsByRole(null,ApplicationConstants.MODULE_FORM_INSTALLMENT,ApplicationConstants.APPL_MODULE_INSTALLMENT);
            model.addAttribute("buttons", buttons.getData());
            model.addAttribute("categoryCode", "INSTALLMENT");
        }catch (Exception e){
            e.printStackTrace();
            log.error("Error At Landin Page",e.getMessage());
        }

        return "site.fpo.installment";
    }

    @GetMapping("/edit")
    public String installmentEdit(Model model,Long instId,String msStatus){
        ServiceOutcome<List<Milstone>> msList= new ServiceOutcome<>();
        try{
            ServiceOutcome<MilestoneCollectorParentVO> editInst = installmentService.fetchInstData(instId,msStatus);
            model.addAttribute("msData",editInst.getData());

            List<FarmerInstallment> instList=installmentService.getAllInstallmentDetails();
            model.addAttribute("installment",instList);
            model.addAttribute("categoryCode", "INSTALLMENT");
            ServiceOutcome<List<StageForwardedRule>> buttons=workFlowService.getButtonsByRole(instId,ApplicationConstants.MODULE_FORM_INSTALLMENT,ApplicationConstants.APPL_MODULE_INSTALLMENT);
            model.addAttribute("buttons", buttons.getData());
            model.addAttribute("instId",editInst.getData().getInst().getId());

        }catch (Exception e){
            e.printStackTrace();
            log.error("Error At Landin Page",e.getMessage());
        }

        return "site.fpo.installment";
    }


    @GetMapping("/milstone")
    public  String FetchMilestone(Long instId,String msStatus, RedirectAttributes attr){
        ServiceOutcome<List<Milstone>> msList= new ServiceOutcome<>();
        try{
//             msList=installmentService.fetchMilestone(instId);
//            attr.addFlashAttribute("msList",msList.getData());
            ServiceOutcome<MilestoneCollectorParentVO> editInst = installmentService.fetchInstData(instId,msStatus);
            attr.addFlashAttribute("msData",editInst.getData());
            attr.addFlashAttribute("instId",instId);
        }catch (Exception e){
            e.printStackTrace();
            log.error("Error At Landin Page",e.getMessage());
        }
        return "redirect:/installment";
    }

    @PostMapping("/milstoneManage")
    public String MilestoneUpdation(MilestoneCollectorParentVO mcpVO,RedirectAttributes attr){
        try{
            ServiceOutcome<Boolean> milestoneUpdation=installmentService.manageMilestone(mcpVO);
            if(milestoneUpdation.getData()) {
                attr.addFlashAttribute("success_msg", milestoneUpdation.getMessage());
            }else {
                attr.addFlashAttribute("error_msg", milestoneUpdation.getMessage());
            }
        }catch(Exception e){

        }
        return "redirect:/installment";
    }
    
    @GetMapping("/list")
    public String milestoneCollectorList(Model model){
        try{
        	ServiceOutcome<List<MilestoneCollectorParent>> allMilestoneCollector = installmentService.getAllMilestoneCollector();
            model.addAttribute("allMilestoneCollector",allMilestoneCollector.getData());
        }catch (Exception e){
            e.printStackTrace();
            log.error("Exception occurred in milestoneCollectorList() ->InstallmentController"+ e.getMessage());
        }
        return "site.fpo.milestoneCollectorList";
    }


}
