package ru.skilanov.model;

import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User {
    private int id;
    private String name;
    private String login;
    private String password;
    private String email;
    private String city;
    private Role role;
    private Timestamp registrationDate;

    public User(String name, String login, String password, String email, String city, Role role, Timestamp registrationDate) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.city = city;
        this.role = role;
        this.registrationDate = registrationDate;
    }
}
