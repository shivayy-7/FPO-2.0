package com.fpo.web.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {"com.aashdit.umt.*","com.fpo.*"})
@EnableJpaRepositories(basePackages = {"com.aashdit.*","com.fpo.*"})
@EntityScan(basePackages = {"com.aashdit.*","com.fpo.*"})
@EnableAsync
public class FPOApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(FPOApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(FPOApplication.class);
	}
	
}
