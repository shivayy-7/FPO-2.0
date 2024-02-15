package com.fpo.web.controllers;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aashdit.framework.core.ServiceOutcome;
import com.fpo.web.entities.Component;
import com.fpo.web.entities.Scheme;
import com.fpo.web.services.ArticleMasterService;
import com.fpo.web.vos.MasterVO;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/master")
@Controller
@Slf4j
public class ArticleMasterController {

	final static Logger logger = Logger.getLogger(ArticleMasterController.class);
	
    @Autowired
    private ArticleMasterService articleMasterService;
                                    
	@Autowired
	private MessageSource messageSource;

    @GetMapping(path = "/map")
    public String articleMasterGET(String masterCall , @RequestParam(name = "childCode" , required = false) String childCode ,Model model) {
        try {
            ServiceOutcome<MasterVO> masterVO = articleMasterService.getMasterData(new MasterVO(),childCode,masterCall,"GET");
            model.addAttribute("masterVO", masterVO.getData());
            model.addAttribute("masterCall", masterCall);
            ServiceOutcome<List<Component>> componentList = articleMasterService.componentCodeList();
            model.addAttribute("componentList",componentList.getData());
        }catch (Exception e) {
            log.error("Error in ArticleMasterController.articleMasterGET() : ", e);
        }
        return "site.articleMaster";
    }

    @PostMapping(path = "/map")
    public String articleMasterPOST(MasterVO masterVO , String masterCall,RedirectAttributes attr) {
        try {
            ServiceOutcome<MasterVO> masterDetails = articleMasterService.getMasterData(masterVO,null,masterCall,"POST");
            attr.addFlashAttribute(masterDetails.getOutcome() ? "success_msg" : "error_msg",masterDetails.getMessage());
        }catch (Exception e) {
            log.error("Error in ArticleMasterController.articleMasterGET() : ", e);
        }
        return "redirect:/master/map?masterCall="+masterCall;
    }
    
    @GetMapping(path = "/actInAct")
    public String actInactive(MasterVO masterVO , String masterCall,Boolean isActive,RedirectAttributes attr){
    	try {
    		ServiceOutcome<MasterVO> srvc = articleMasterService.mstActiveInActive(masterVO,masterCall,isActive);
    		attr.addFlashAttribute(srvc.getOutcome() ? "success_msg" : "error_msg",srvc.getMessage());
    	}catch(Exception e) {
    		logger.error(e);
    		attr.addFlashAttribute("error_msg", messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
    	}
    	return "redirect:/master/map?masterCall="+masterCall;
    }

    //component
    
    @GetMapping( "/addComponent")
    public String add(Model model) {
        try {
			ServiceOutcome<List<Component>> staOutcome=articleMasterService.listComponent(true);
			ServiceOutcome<List<Scheme>> srvcCome = articleMasterService.listSchemeName();
			if(srvcCome.getOutcome()) {
				model.addAttribute("schemeNameList" , srvcCome.getData());
			}
			if(staOutcome.getOutcome()) {
				model.addAttribute("ComponentList", staOutcome.getData());
			}else {
				model.addAttribute("error_msg", staOutcome.getMessage());
			}
        } catch (Exception ex) {
        	ex.printStackTrace();
        	logger.error(ex);
			model.addAttribute("error_msg", messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));

        }

        return "site.component.add";
    }
    
    @PostMapping( "/saveComponent")
    public String saveComponent(@ModelAttribute("component") Component component,
            RedirectAttributes attr) {
        try {
			ServiceOutcome<Component> serviceOutcome=articleMasterService.saveComponent(component);
			attr.addFlashAttribute(serviceOutcome.getOutcome() ? "success_msg" : "error_msg",serviceOutcome.getMessage());
		
        } catch (Exception ex) {
            logger.error(ex);
            attr.addFlashAttribute("error_msg", messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
        }
    	return "redirect:/master/addComponent";
    }
    
    
    @GetMapping( "/editComponent/{cmpId}")
    public String edit(@PathVariable("cmpId")Long cmpId, RedirectAttributes Attributes ) {
        try {
			ServiceOutcome<Component> serviceOutcome=articleMasterService.getComponent(cmpId);
			if(serviceOutcome.getOutcome()) {
				Attributes.addFlashAttribute("componentData", serviceOutcome.getData());
				
			}else {
				Attributes.addFlashAttribute("error_msg", serviceOutcome.getMessage());
			}
			
        } catch (Exception ex) {
            logger.error(ex);
            Attributes.addFlashAttribute("error_msg", messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));

        }
        return "redirect:/master/addComponent";
    }
    	
	//AgencyType
	
	 
					  
}
