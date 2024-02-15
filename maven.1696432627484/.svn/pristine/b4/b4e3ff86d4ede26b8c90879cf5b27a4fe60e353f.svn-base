package com.fpo.web.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

import com.aashdit.umt.model.User;

@Component
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class PersistenceConfig {

    @Bean
    AuditorAware<User> auditorProvider() {
        return new AuditorAwareImpl();
    }
}
