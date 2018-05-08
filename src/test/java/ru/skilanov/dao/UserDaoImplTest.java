package ru.skilanov.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;
import ru.skilanov.model.Role;
import ru.skilanov.model.User;

import java.sql.Timestamp;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Класс тестирует dao пользователей.
 */
public class UserDaoImplTest {

    /**
     * Dao пользователей.
     */
    private UserDao userDao;

    /**
     * Перед тестом.
     *
     * @throws Exception Exception
     */
    @Before
    public void setUp() throws Exception {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        userDao = new UserDaoImpl(factory);
    }

    /**
     * Тестирует удаление пользователя.
     */
    @Test
    public void whenDeleteUser_ThenUserDeleted() {
        User userSam = new User("sam", "sam", "1", "mail", "moscow", Role.USER,
                new Timestamp(System.currentTimeMillis()));

        userDao.insert(userSam);
        userDao.deleteUser(1);

        assertNull(userDao.findById(1));
    }

    /**
     * Тестирует получение всех пользователей.
     */
    @Test
    public void whenGetAll_ThenReturnListOfUsers() {
        User userSam = new User("sam", "sam", "1", "mail", "moscow", Role.USER,
                new Timestamp(System.currentTimeMillis()));
        User userMike = new User("mike", "mike", "2", "mails", "moscow", Role.USER,
                new Timestamp(System.currentTimeMillis()));

        userDao.insert(userSam);
        userDao.insert(userMike);

        int size = userDao.getAll().size();

        assertThat(size, is(2));
    }

    /**
     * Тестирует ввод нового пользователя.
     */
    @Test
    public void whenInsertNewUser_ThenUserInserted() {
        User userSam = new User("sam", "sam", "1", "mail", "moscow", Role.USER,
                new Timestamp(System.currentTimeMillis()));

        userDao.insert(userSam);

        assertNotNull(userDao.findById(1));
    }

    /**
     * Тестирует изменение пользователя.
     */
    @Test
    public void whenUpdateUser_ThenUserUpdated() {
        User userSam = new User("sam", "sam", "1", "mail", "moscow", Role.USER,
                new Timestamp(System.currentTimeMillis()));

        userDao.insert(userSam);

        User user = userDao.findById(1);
        user.setName("Semyon");
        userDao.update(user);

        String expectedName = userDao.getAll().get(0).getName();

        assertThat(expectedName, is("Semyon"));
    }

    /**
     * Тестирует поиск пользователя по id.
     */
    @Test
    public void whenFindById_ThenReturnRightResult() {
        User userSam = new User("sam", "sam", "1", "mail", "moscow", Role.USER,
                new Timestamp(System.currentTimeMillis()));

        userDao.insert(userSam);

        User expectedUser = userDao.findById(1);

        assertThat(expectedUser, is(userSam));
    }

    /**
     * Тестирует поиск пользователя по логину и паролю.
     */
    @Test
    public void whenFindByLoginAndPassword_ThenReturnRightResult() {
        User userSam = new User("sam", "sam", "1", "mail", "moscow", Role.USER,
                new Timestamp(System.currentTimeMillis()));

        userDao.insert(userSam);

        User expectedUser = userDao.finedByLoginAndPassword("sam", "1");

        assertThat(expectedUser, is(userSam));
    }

    /**
     * Тестирует существует ли пользователь.
     */
    @Test
    public void whenCheckIsUserExist_ThenReturnTrue() {
        User userSam = new User("sam", "sam", "1", "mail", "moscow", Role.USER,
                new Timestamp(System.currentTimeMillis()));

        userDao.insert(userSam);

        boolean result = userDao.isUserExist("sam", "1");

        assertThat(result, is(true));
    }
}
