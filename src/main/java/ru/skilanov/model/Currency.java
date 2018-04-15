package ru.skilanov.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Currency implements Serializable{
    private int id;
    private String name;

    public Currency(String name) {
        this.name = name;
    }
}
