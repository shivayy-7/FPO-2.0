package com.fpo.web.securities;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

//@Component
public class NonceCSRFFilter extends OncePerRequestFilter {


	private final CsrfTokenRepository csrfTokenRepository;
	
	final RequestMatcher requireCsrfProtectionMatcher = new DefaultRequiresCsrfMatcher();
	final AccessDeniedHandler accessDeniedHandler = new AccessDeniedHandlerImpl();

	public NonceCSRFFilter(CsrfTokenRepository tokenRepository)
	{
		this.csrfTokenRepository = tokenRepository;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	
		
		
		if(!request.getMethod().equals("OPTIONS")) {
			CsrfToken sessionCSRFToken = csrfTokenRepository.loadToken(request);
			
			final boolean missingToken = sessionCSRFToken == null;
			if (missingToken) {
				sessionCSRFToken = this.csrfTokenRepository.generateToken(request);
				this.csrfTokenRepository.saveToken(sessionCSRFToken, request, response);
			}
			
			request.setAttribute(CsrfToken.class.getName(), sessionCSRFToken);
			request.setAttribute(sessionCSRFToken.getParameterName(), sessionCSRFToken);

			if (!this.requireCsrfProtectionMatcher.matches(request)) {
				filterChain.doFilter(request, response);
				return;
			}
			else
			{
				final String hdnCSRFTokenValue = request.getParameter("_csrf");
				if(!request.getRequestURI().contains("/transaction/apply/") && !request.getRequestURI().contains("/api/login") && !request.getRequestURI().contains("/api/v1/save-fund-disbursement") && !request.getRequestURI().contains("/api/expenditure/actionPlanFilter")
				                   && !request.getRequestURI().contains("/api/expenditure/create-update-expenditure")
				                   && !request.getRequestURI().contains("/api/expenditure/delete-expenditure")
				                   && !request.getRequestURI().contains("/api/expenditure/expenditure-final-submit")) {
					if (!hdnCSRFTokenValue.equals(sessionCSRFToken.getToken()))
					{
						accessDeniedHandler.handle(request, response, new AccessDeniedException(
								"Missing or non-matching CSRF-token"));
						return;
					}
					else
					{
						//Regenerate the token
						//Code from onAuthentication Method of
						//https://github.com/spring-projects/spring-security/blob/master/web/src/main/java/org/springframework/security/web/csrf/CsrfAuthenticationStrategy.java
						this.csrfTokenRepository.saveToken(null, request, response);
						
						sessionCSRFToken = csrfTokenRepository.generateToken(request);
						csrfTokenRepository.saveToken(sessionCSRFToken, request, response);
						
						request.setAttribute(CsrfToken.class.getName(), sessionCSRFToken);
						request.setAttribute(sessionCSRFToken.getParameterName(), sessionCSRFToken);
					}
				}
				filterChain.doFilter(request, response);
			}
		} else {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
		}
	}

	public static final class DefaultRequiresCsrfMatcher implements RequestMatcher {
		private final Pattern allowedMethods = Pattern.compile("^(GET|HEAD)$");

		@Override
		public boolean matches(HttpServletRequest request) {
			boolean noMatch =  !allowedMethods.matcher(request.getMethod()).matches();
			
			//Exempt AJAX Calls from CSRF Requirement
			if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With")))
			{
				noMatch = false;
			}
			
			return noMatch;
		}
	}

}