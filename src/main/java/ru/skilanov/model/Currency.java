package ru.skilanov.model;

import lombok.*;

import java.io.Serializable;

/**
 * Сущность валюты.
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Currency implements Serializable {
    public static final String ID = "id";
    public static final String NAME = "name";
    /**
     * id.
     */
    private int id;
    /**
     * имя.
     */
    private String name;

    /**
     * Перегрузка онструктора.
     *
     * @param name String
     */
    public Currency(String name) {
        this.name = name;
    }
}
