package ru.skilanov.dao;

import ru.skilanov.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAll();

    void insert(User user);

    void delete(int id);

    void update(User user);

    User findById(int id);
}
