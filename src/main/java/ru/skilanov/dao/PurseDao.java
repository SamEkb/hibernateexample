package ru.skilanov.dao;

import ru.skilanov.model.Purse;

import java.util.List;

/**
 * Интерфейс дао для кошельков.
 */
public interface PurseDao {
    /**
     * Метод возвращает список всех кошельков.
     *
     * @return List
     */
    List<Purse> getAll();

    /**
     * Метод добавления нового кошелька.
     *
     * @param purse Purse
     */
    void insert(Purse purse);

    /**
     * Метод удаления кошелька.
     *
     * @param id int
     */
    void delete(int id);

    /**
     * Метод редактирования кошелька.
     *
     * @param purse Purse
     */
    void update(Purse purse);

    /**
     * Метод поиска по id
     *
     * @param id int
     * @return Purse
     */
    Purse findById(int id);

    /**
     * Метод возвращает все кошельки пользователя.
     *
     * @param id int
     * @return List
     */
    List<Purse> getAllUserPurses(int id);
}
