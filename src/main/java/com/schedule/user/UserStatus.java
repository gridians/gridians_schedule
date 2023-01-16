package com.schedule.user;

public enum UserStatus {
    ACTIVE("active"), UNACTIVE("unactive"), BLOCKED("block");


    private String value;

    UserStatus(String value) {
        this.value = value;
    }
}
