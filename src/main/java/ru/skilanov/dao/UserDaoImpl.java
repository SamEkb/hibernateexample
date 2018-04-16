package ru.skilanov.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.skilanov.model.User;

import java.util.List;

public class UserDaoImpl implements UserDao {

    private SessionFactory factory;

    public UserDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void deleteUser(int id) {
        deletePursesOfUser(id);
        delete(id);
    }

    private void delete(int id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            User user = new User();
            user.setId(id);
            session.delete(user);

            session.getTransaction().commit();
        }
    }

    private void deletePursesOfUser(int id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            Query query = session.createQuery("delete from Purse where user.id=:id");
            query.setParameter("id", id);
            query.executeUpdate();

            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAll() {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            List<User> users = session.createQuery("FROM User").list();

            session.getTransaction().commit();

            return users;
        }
    }

    @Override
    public void insert(User user) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.save(user);

            session.getTransaction().commit();
        }
    }

    @Override
    public void update(User user) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.update(user);

            session.getTransaction().commit();
        }
    }

    @Override
    public User findById(int id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            User user = session.get(User.class, id);

            session.getTransaction().commit();

            return user;
        }
    }

    public User finedByLogin(String login, String password) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            Query query = session.createQuery("from User where login=:login and password=:password");
            query.setParameter("login", login);
            query.setParameter("password", password);

            List<User> users = query.list();

            for (User user1 : users) {
                return user1;
            }

            session.getTransaction().commit();
        }
        return null;
    }

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
