package com.fpo.web.securities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter.ReferrerPolicy;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.aashdit.umt.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"com.fpo.*"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;
    
//    @Autowired
//    private NonceCSRFFilter nonceCSRFFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncod());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncod() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off

        http.headers() // Security Fix #3 - Vishnoo
                .contentTypeOptions()
                .and()
                .xssProtection()
                .and()
                .cacheControl()
                .and()
                .httpStrictTransportSecurity()
                .and()
                .frameOptions()
                .and()
                .contentSecurityPolicy("script-src 'self' 'unsafe-eval' 'unsafe-inline'")
                .and()
                .referrerPolicy(ReferrerPolicy.SAME_ORIGIN);

        http.authorizeRequests()
                .antMatchers(HttpMethod.TRACE, "/**").denyAll() // Security Fix #2 - Vishnoo
                .antMatchers(HttpMethod.PATCH, "/**").denyAll().antMatchers(HttpMethod.PUT, "/**").denyAll()
                .antMatchers(HttpMethod.DELETE, "/**").denyAll().antMatchers(HttpMethod.HEAD, "/**").denyAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").denyAll()
                .antMatchers("/captcha/**").permitAll()
                .antMatchers("/loginPage/**").permitAll()
                .antMatchers("/dashboard/**").permitAll()
                .antMatchers("/cbbo/**").permitAll()
                .antMatchers("/farmer/**").permitAll()
                .antMatchers("/reset/**").permitAll()
                .antMatchers("/assets/**").permitAll()
                .antMatchers("/overwrite/**").permitAll()
                .antMatchers("/privacyPolicy").permitAll()
                .antMatchers("/installment/**").permitAll()
                // .antMatchers("/password").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/system/**").hasAnyRole("SYSTEM")
                .antMatchers("/admin/**").hasAnyRole("SYSTEM", "ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout()
                //.logoutUrl("/umt/logout")
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true).deleteCookies("FARMER-MAIN-MANGAL", "JSESSIONID").permitAll();

        http.cors().and().csrf().ignoringAntMatchers("/ajax/**","/dashboard/**","/api/**", "/api/login");
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        // @formatter:on
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        SessionRegistry sessionRegistry = new SessionRegistryImpl();
        return sessionRegistry;
    }

    @Bean
    public HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository() {
        return new HttpSessionCsrfTokenRepository();
    }

}