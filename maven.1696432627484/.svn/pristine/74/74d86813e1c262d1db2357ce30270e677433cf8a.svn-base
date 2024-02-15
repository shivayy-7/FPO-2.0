package com.fpo.web.configs;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperConfiguration {
	
	@Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

	@Bean
	public MessageSource webMvcMessageSource() {
	    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	    messageSource.setBasename("messages");
	    return messageSource;
	}

	
}
