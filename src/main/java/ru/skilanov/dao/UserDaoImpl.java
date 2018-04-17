package ru.skilanov.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.skilanov.model.User;

import java.util.List;

/**
 * Реализация интерфейса UserDao.
 */
public class UserDaoImpl implements UserDao {

    /**
     * Фабрика сессий hibernate.
     */
    private SessionFactory factory;

    /**
     * Конструктор.
     *
     * @param factory SessionFactory
     */
    public UserDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    /**
     * Метод удаляет пользователя и его кошельки.
     *
     * @param id int
     */
    @Override
    public void deleteUser(int id) {
        deletePursesOfUser(id);
        delete(id);
    }

    /**
     * Метод удаляет пользователя.
     *
     * @param id int
     */
    private void delete(int id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            User user = new User();
            user.setId(id);
            session.delete(user);

            session.getTransaction().commit();
        }
    }

    /**
     * Мето удаляет кошельки пользователя.
     *
     * @param id int
     */
    private void deletePursesOfUser(int id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            Query query = session.createQuery("delete from Purse where user.id=:id");
            query.setParameter("id", id);
            query.executeUpdate();

            session.getTransaction().commit();
        }
    }

    /**
     * Метод возвращает список всех пользователей.
     *
     * @return List
     */
    @Override
    public List<User> getAll() {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            List<User> users = session.createQuery("FROM User").list();

            session.getTransaction().commit();

            return users;
        }
    }

    /**
     * Метод добавляет нового пользователя.
     *
     * @param user User
     */
    @Override
    public void insert(User user) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.save(user);

            session.getTransaction().commit();
        }
    }

    /**
     * Метод обновляет пользователя.
     *
     * @param user User
     */
    @Override
    public void update(User user) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.update(user);

            session.getTransaction().commit();
        }
    }

    /**
     * Метод поиска пользователя по id.
     *
     * @param id int
     * @return User
     */
    @Override
    public User findById(int id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            User user = session.get(User.class, id);

            session.getTransaction().commit();

            return user;
        }
    }

    /**
     * Метод ищет пользователя по логину и паролю.
     *
     * @param login    String
     * @param password String
     * @return User
     */
    @Override
    public User finedByLoginAndPassword(String login, String password) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            Query query = session.createQuery("from User where login=:login and password=:password");
            query.setParameter("login", login);
            query.setParameter("password", password);

            List<User> users = query.list();

            for (User user : users) {
                return user;
            }

            session.getTransaction().commit();
        }
        return null;
    }

    /**
     * Метод проверяет существует ли пользователь.
     *
     * @param login    String
     * @param password String
     * @return boolean
     */
    @Override
    public boolean isUserExist(String login, String password) {
        boolean result = false;
        Session session = factory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("from User where login=:login and password=:password");
        query.setParameter("login", login);
        query.setParameter("password", password);

        List<User> users = query.list();

        if (users.size() > 0) {
            result = true;
        }
        return result;
    }
}
