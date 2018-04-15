package ru.skilanov.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.skilanov.model.Purse;
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

    public List<Purse> getAllUserPurses(int id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            Query query = session.createQuery("from Purse where user.id=:id");
            query.setParameter("id", id);

            List<Purse> list = query.list();

            session.getTransaction().commit();

            return list;
        }
    }
}
