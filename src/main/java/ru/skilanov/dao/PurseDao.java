package ru.skilanov.dao;

import ru.skilanov.model.Purse;

import java.util.List;

public interface PurseDao {
    List<Purse> getAll();

    void insert(Purse purse);

    void delete(int id);

    void update(Purse purse);

    Purse findById(int id);

    List<Purse> getAllUserPurses(int id);
}
