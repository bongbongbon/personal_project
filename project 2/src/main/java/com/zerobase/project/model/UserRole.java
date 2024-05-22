package com.zerobase.project.model;


import lombok.Getter;
import org.springframework.web.bind.annotation.ModelAttribute;

@Getter
public enum UserRole {
    PARTNER("ROLE_PARTNER"),
    USER("ROLE_USER");

    UserRole(String value) {
        this.value = value;
    }

    private String value;
}

