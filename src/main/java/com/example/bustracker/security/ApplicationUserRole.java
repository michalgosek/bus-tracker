package com.example.bustracker.security;


public class ApplicationUserRole {
    private final Long userId;
    private final Long roleId;

    public ApplicationUserRole(Long userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getRoleId() {
        return roleId;
    }
}
