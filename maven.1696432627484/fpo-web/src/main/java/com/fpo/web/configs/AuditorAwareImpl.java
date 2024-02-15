package com.fpo.web.configs;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

import com.aashdit.umt.model.User;
import com.aashdit.umt.util.SecurityHelper;

public class AuditorAwareImpl implements AuditorAware<User> {

    @Override
    public Optional<User> getCurrentAuditor() {
        return Optional.of(SecurityHelper.getCurrentUser());
    }

}
