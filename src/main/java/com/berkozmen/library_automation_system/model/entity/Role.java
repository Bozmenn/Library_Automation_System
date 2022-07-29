package com.berkozmen.library_automation_system.model.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public enum Role implements GrantedAuthority {

    ROLE_ADMIN,
    ROLE_CLIENT;

    @Override
    public String getAuthority() {
        return name().toString();
    }
}
