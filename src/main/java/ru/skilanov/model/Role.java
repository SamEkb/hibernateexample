package ru.skilanov.model;

public enum Role {
    ADMIN, USER, UNKNOWN;

    public static Role getRole(String name) {
        Role role = null;
        if ("admin".equalsIgnoreCase(name)) {
            role = Role.ADMIN;
        } else if ("user".equalsIgnoreCase(name)) {
            role = Role.USER;
        }
        return role;
    }
}
