package com.example.ncn.model;

public enum Permission {
    CLIENT_BAN("client:ban"),
    CLIENT_WRITE("client:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
