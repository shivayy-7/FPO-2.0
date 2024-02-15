package com.fpo.web.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "fpo")
@PropertySource(value = "classpath:application.yml", factory = YamlPropertySourceFactory.class)
@Data
public class YamlAppProperties {

    private String path;
    
    private Boolean auth;

    private Boolean enable;

    private String host;
    
    private String trust;

    private String port;

    private String protocol;

    private String username;

    private String password;
    
    
}


