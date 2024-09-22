package com.msa.springbaseproject.common;

import com.msa.springbaseproject.business.user.model.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class Utilities {
    public static Optional<User> getLoginUser() {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            Object details = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (details instanceof User) {
                return Optional.of((User) details);
            }
        }
        return Optional.empty();
    }
}
