package com.fpo.web.services;

import com.aashdit.framework.core.ServiceOutcome;

public interface ForgotPasswordService {

	ServiceOutcome<Boolean> forgotPassword(String username);

}
