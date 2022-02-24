package com.shop.global.utils.emuns;

public enum UserRole implements EnumModel {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

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