package ru.skilanov.model;

import java.sql.Timestamp;

public class Item {
    private int id;
    private String description;
    private Timestamp created;
    private String done;

    public Item(String description, Timestamp created, String done) {

        this.description = description;
        this.created = created;
        this.done = done;
    }

    public Item(int id, String description, Timestamp created, String done) {

        this.id = id;
        this.description = description;
        this.created = created;
        this.done = done;
    }

    public Item() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }


    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", created=" + created +
                ", done='" + done + '\'' +
                '}';
    }

}
