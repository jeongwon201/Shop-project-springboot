package com.shop.global.utils.emuns;

public enum UserRole implements EnumModel {
    ADMIN("ROLE_ADMIN"),
    STAFF("ROLE_STAFF"),
    USER("ROLE_USER"),
    GUEST("ROLE_GUEST");

    private final String roleName;

    UserRole(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return roleName;
    }
    
}