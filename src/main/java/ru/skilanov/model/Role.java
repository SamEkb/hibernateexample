package ru.skilanov.model;

/**
 * Роль.
 */
public enum Role {
    ADMIN, USER, UNKNOWN;

    /**
     * Возвращает роль.
     *
     * @param name String
     * @return Role
     */
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
