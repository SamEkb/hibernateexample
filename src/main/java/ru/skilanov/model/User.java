package ru.skilanov.model;

import lombok.*;

import java.sql.Timestamp;

/**
 * Сущность пользователя.
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User {

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String CITY = "city";
    public static final String ROLE = "role";
    public static final String REGISTRATION_DATE = "registrationDate";

    /**
     * id.
     */
    private int id;
    /**
     * Имя.
     */
    private String name;
    /**
     * Логин.
     */
    private String login;
    /**
     * Пароль.
     */
    private String password;
    /**
     * Электронный адресс.
     */
    private String email;
    /**
     * Город.
     */
    private String city;
    /**
     * Роль.
     */
    private Role role;
    /**
     * Дата регистрации.
     */
    private Timestamp registrationDate;

    /**
     * Перегрузка конструктора.
     *
     * @param name             String
     * @param login            String
     * @param password         String
     * @param email            String
     * @param city             String
     * @param role             Role
     * @param registrationDate Timestamp
     */
    public User(String name, String login, String password, String email, String city, Role role, Timestamp registrationDate) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.city = city;
        this.role = role;
        this.registrationDate = registrationDate;
    }

    /**
     * Перегрузка конструктора.
     *
     * @param name     String
     * @param login    String
     * @param password String
     * @param email    String
     * @param city     String
     * @param role     Role
     */
    public User(String name, String login, String password, String email, String city, Role role) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.city = city;
        this.role = role;
    }

    /**
     * Перегрузка конструктора.
     *
     * @param name             String
     * @param login            String
     * @param password         String
     * @param email            String
     * @param role             Role
     * @param registrationDate Timestamp
     */
    public User(String name, String login, String password, String email, Role role, Timestamp registrationDate) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
        this.registrationDate = registrationDate;
    }
}
