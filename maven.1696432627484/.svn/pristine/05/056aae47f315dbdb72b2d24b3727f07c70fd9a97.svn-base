package com.fpo.web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aashdit.framework.core.ServiceOutcome;
import com.aashdit.framework.core.util.PasswordValidator;
import com.aashdit.umt.model.User;
import com.aashdit.umt.service.UserService;
import com.fpo.web.services.ForgotPasswordService;
import com.fpo.web.utils.AesUtil;

@Controller
@RequestMapping("/reset")
public class ForgotPasswordController {
final static Logger logger = Logger.getLogger(ForgotPasswordController.class);
	
	//ResourceBundle rb = ResourceBundle.getBundle("application.yml");

	
	@Autowired
	private ForgotPasswordService forgotPasswordService;
	
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value="/forgot-password", name="Reset Password")
	public String forgotPassword(RedirectAttributes attribute,String usernm) {
		try {
			ServiceOutcome<Boolean> result = forgotPasswordService.forgotPassword(usernm);
			if(result.getData()){
				attribute.addFlashAttribute("success_msg", result.getMessage());
			}else {
				attribute.addFlashAttribute("err_msg", result.getMessage());
			}
		} catch (Exception e) {
			logger.error("Exception occured in forgotPassword method in ForgotPasswordController-->"+e.getMessage());
		}
		return "redirect:/login";
	}
	
	@PostMapping(path = "/umt/user/change/password/submit", name = "Change Password")
	public String changePassword(RedirectAttributes attr, HttpSession session, String userId, String txtPass,
			String txtRePass, String currPass, HttpServletRequest request) {
		try {
			Boolean isOK = Boolean.valueOf(true);
			String realCurrentPass = "";
			String realPass = "";
			String realRePass = "";
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			User user = null;
			if (userId != null) {
				user = userService.findByUsername(userId).getData();
			} else {
				session.invalidate();
				return "redirect:/login";
			}
			String decryptedCurrentPassword = new String(java.util.Base64.getDecoder().decode(currPass));
			String psk = request.getParameter("_csrf");
			psk = psk.substring(0, 16);
			AesUtil aesUtil = new AesUtil(128, 1000);
			if (decryptedCurrentPassword != null && decryptedCurrentPassword.split("::").length == 3) {
				realCurrentPass = aesUtil.decrypt(decryptedCurrentPassword.split("::")[1],
						decryptedCurrentPassword.split("::")[0], psk, decryptedCurrentPassword.split("::")[2]);
			}
			isOK = Boolean.valueOf(passwordEncoder.matches(realCurrentPass, user.getPassword()));
			if (!isOK.booleanValue()) {
				attr.addFlashAttribute("error_msg", "Sorry, Curent Password not matched please try again !");
				logger.error("Error Msg : Sorry, Curent Password not matched please try again during change password !");
				return "redirect:/umt/user/change/password";
			} else {
				boolean chk = false;
				if (txtRePass != null && !txtRePass.trim().isEmpty() && txtPass != null && !txtPass.trim().isEmpty()) {
					String decryptedPassword = new String(java.util.Base64.getDecoder().decode(txtPass));
					if (decryptedPassword != null && decryptedPassword.split("::").length == 3) {
						realPass = aesUtil.decrypt(decryptedPassword.split("::")[1], decryptedPassword.split("::")[0],psk, decryptedPassword.split("::")[2]);
					}
					String decryptedRePassword = new String(java.util.Base64.getDecoder().decode(txtRePass));
					if (decryptedRePassword != null && decryptedRePassword.split("::").length == 3) {
						realRePass = aesUtil.decrypt(decryptedRePassword.split("::")[1],decryptedRePassword.split("::")[0], psk, decryptedRePassword.split("::")[2]);
					}
					chk = PasswordValidator.checkString(realPass);
					if (chk) {
						if (realPass.equals(realRePass)) {
							Boolean isSuccess = userService.saveResetPassword(user.getUserId(), realRePass);
							if (isSuccess) {
								session.invalidate();
								return "redirect:/login";
							} else {
								return "redirect:/umt/user/change/password";
							}

						} else {
							attr.addFlashAttribute("error_msg", "Password not match with password!");
							logger.error("Error Msg : Password not match with password!");
							return "redirect:/umt/user/change/password";
						}
					} else {
						attr.addFlashAttribute("error_msg", "Password not match with password policy !");
						logger.error("Error Msg : Password not match with password policy !");
						return "redirect:/umt/user/change/password";
					}
				} else {
					attr.addFlashAttribute("error_msg", "Password not match with password policy !");
					logger.error("Error Msg : Password not match with password policy !");
					return "redirect:/umt/user/change/password";
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			attr.addFlashAttribute("error_msg", "Unable to update profile");
			return "redirect:/umt/user/change/password";
		}

	}

}
