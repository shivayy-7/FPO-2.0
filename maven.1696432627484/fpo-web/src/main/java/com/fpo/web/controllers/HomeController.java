package com.fpo.web.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.aashdit.framework.core.ServiceOutcome;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController implements MessageSourceAware{
	
	
	@Autowired
	private MessageSource messageSource;
	
//	public static ResourceBundle rb = ResourceBundle.getBundle("application");
	
	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;

	}

	@GetMapping(path ={"/home","/"})
	public String home(Model model, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		try {
//			User user =SecurityHelper.getCurrentUser();
//			System.out.println(user.getPrimaryRole().getRoleCode());
//
//			System.out.println(userDataCollector.findAgencyRegistrationCodeFromCurrentUser());
//
//			System.out.println(userDataCollector.findObjectFromCurrentUser().getData().toString());
			
			
			
			
//			Cookie[] cookies = httpServletRequest.getCookies();
//			for (Cookie ck : cookies) {
//	            if (rb.getString("ws.cookiesName").equals(ck.getName())) {
//	            	httpServletResponse.setHeader("Set-Cookie", "PANI_PANCHAYAT="+ck.getValue() +";HttpOnly=true; SameSite=lax; Secure;Max-Age=1800");
//	            }
//			}
			
//			List<FinancialYear> finYearDtoList = commonService.getAllFinancialYears().getData();
//			List<Month> monthList = commonService.findAllMonthList();
//			model.addAttribute("monthList",monthList);
//			model.addAttribute("finYearList",finYearDtoList);
//			model.addAttribute("selFromDate",new SimpleDateFormat("dd/MM/yyyy").format(commonService.getAllFinancialYears().getData().stream().reduce((first, second) -> second).get().getStartDate()));
//			model.addAttribute("selToDate",new SimpleDateFormat("dd/MM/yyyy").format(commonService.getAllFinancialYears().getData().stream().findFirst().orElse(null).getEndDate()));
//			

		} catch (Exception e) {
			log.error("Error At Landin Page",e.getMessage());
		}
		return "site.dashboard";
	}
	
	@GetMapping(path ={"/login"})
	public String loginGET(Principal principal, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		String returnString="";
		try {
			if(Optional.ofNullable(principal).isPresent()) {
				returnString="redirect:/";
			}else {
				returnString="layout.login";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnString;
	}



	
}
