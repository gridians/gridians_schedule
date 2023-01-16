package com.schedule.user;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN("ROLE_ADMIN"), USER("ROLE_USER"), ANONYMOUS("ROLE_ANONYMOUS");

    private String value;

    Role(String value){
        this.value = value;
    }
}