package com.fpo.web.controllers;

import com.aashdit.framework.core.ServiceOutcome;
import com.fpo.web.entities.FarmerCbbo;
import com.fpo.web.services.CBBOServices;
import com.fpo.web.services.CommonService;
import com.fpo.web.vos.CbboVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;
import java.util.List;
@Controller
@Slf4j
@RequestMapping("/cbbo")
public class CBBOController {

    @Autowired
    private CommonService commonService;

    @Autowired
    private CBBOServices cbboServices;

    @GetMapping
    public String addCBBO(Model model, @RequestParam(value="status", required = false, defaultValue = "SAVE") String status) {
        try{
            model.addAttribute("stateList", commonService.getAllData("State").getData());
            model.addAttribute("bankList", commonService.getAllData("Bank").getData());
            model.addAttribute("blockList", commonService.getAllData("Block").getData());
            model.addAttribute("componentList", commonService.getAllData("Component").getData());
            model.addAttribute("designationList", commonService.getAllData("Designation").getData());
            model.addAttribute("genderList", commonService.getAllData("Gender").getData());
            model.addAttribute("schemeList", commonService.getAllData("Scheme").getData());
            model.addAttribute("status", status);
        }catch (Exception e){
            e.printStackTrace();
        }
        return"site.addCBBO";
    }

    @PostMapping("/manage")
    public String addnUpdateCBBO(CbboVO cbboVO, RedirectAttributes attr) {
        try {
          ServiceOutcome<Boolean> cbbo = cbboServices.manageCBBO(cbboVO);
            if(cbbo.getData()) {
                attr.addFlashAttribute("success_msg", cbbo.getMessage());
            }else {
                attr.addFlashAttribute("error_msg", cbbo.getMessage());
            }
        }catch (Exception e){
        	e.printStackTrace();
        }
        return"redirect:/cbbo";
    }

    @GetMapping("/list")
    public String cbboList(Model model, @RequestParam(value="cbboCode", required=false) String cbboCode, RedirectAttributes attr) {
        try {
           if(Optional.ofNullable(cbboCode).isPresent()){
               ServiceOutcome<CbboVO> existingCbboList = cbboServices.getCbboByCbboCode(cbboCode);
               attr.addFlashAttribute("cbbo", existingCbboList.getData());
               return "redirect:/cbbo?status=UPDATE";
           }else{
               model.addAttribute("cbboList", commonService.getAllData("CBBO").getData());
           }
        }catch (Exception e){
            e.printStackTrace();
        }
        return"site.cbboList";
    }



}
