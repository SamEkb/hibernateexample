package ru.skilanov.dao;

import ru.skilanov.model.User;

import java.util.List;

/**
 * Интерфейс дао пользователя.
 */
public interface UserDao {
    /**
     * Метод возвращает список всех пользователей.
     *
     * @return List
     */
    List<User> getAll();

    /**
     * Метод добавляет нового пользователя.
     *
     * @param user User
     */
    void insert(User user);

    /**
     * Метод удаляет пользователя.
     *
     * @param id int
     */
    void deleteUser(int id);

    /**
     * Метод обновляет пользователя.
     *
     * @param user User
     */
    void update(User user);

    /**
     * Метод поиска пользователя по id.
     *
     * @param id int
     * @return User
     */
    User findById(int id);

    /**
     * Метод проверяет существует ли пользователь.
     *
     * @param login    String
     * @param password String
     * @return boolean
     */
    boolean isUserExist(String login, String password);

    /**
     * Метод ищет пользователя по логину и паролю.
     *
     * @param login    String
     * @param password String
     * @return User
     */
    User finedByLoginAndPassword(String login, String password);
}
