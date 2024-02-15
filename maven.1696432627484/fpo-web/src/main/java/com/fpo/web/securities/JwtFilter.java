package com.fpo.web.securities;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.aashdit.umt.model.LoggedInUser;
import com.aashdit.umt.model.Role;
import com.aashdit.umt.model.User;
import com.aashdit.umt.model.UserRoleMap;
import com.aashdit.umt.service.RoleService;
import com.aashdit.umt.service.UserService;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws ServletException, IOException {
		if (!httpServletRequest.getMethod().equals("OPTIONS")) {
			Boolean skipThis = false;
			if (httpServletRequest.getRequestURI().contains("/api/login")) {
				skipThis = true;
				filterChain.doFilter(httpServletRequest, httpServletResponse);
			} else {
				if (httpServletRequest.getRequestURI().contains("/api") && skipThis == false) {
					String authorizationHeader = httpServletRequest.getHeader("Authorization");
					String token = null;
					String userName = null;
					if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
						token = authorizationHeader.substring(7);
						userName = jwtUtil.extractUsername(token);
						if (userName != null) {
							User user = userService.findByUsername(userName).getData();
							if (user != null) {
								if (jwtUtil.validateToken(token, user)) {

									final List<UserRoleMap> userRoleMaps = this.userService
											.findUserRoleMapByUserId(user.getUserId());
									final Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
									for (final UserRoleMap urm : userRoleMaps) {
										final Role role = this.roleService.findByRoleId(urm.getRoleId()).getData();
										grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleCode()));
									}
									final LoggedInUser userDetails = new LoggedInUser(user.getUserName(),
											user.getPassword(), true, true, true, true, grantedAuthorities,
											user.getPrimaryRole(), user);
									final Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, null,
											grantedAuthorities);
									final SecurityContext sc = SecurityContextHolder.getContext();
									sc.setAuthentication(auth);
									final HttpSession session = httpServletRequest.getSession(true);
									session.setAttribute("SPRING_SECURITY_CONTEXT", sc);
									filterChain.doFilter(httpServletRequest, httpServletResponse);
								} else {
									httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
								}
							} else {
								httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
							}
						} else {
							httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
						}
					} else {
						httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
					}
				} else {
					filterChain.doFilter(httpServletRequest, httpServletResponse);
				}
			}
		} else {
			httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
		}
	}

}