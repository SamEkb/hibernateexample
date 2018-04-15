package ru.skilanov.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.skilanov.model.Role;
import ru.skilanov.model.User;
import ru.skilanov.utils.HibernateFactory;

import java.sql.Timestamp;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private SessionFactory factory = HibernateFactory.getFactory();

//    public static void main(String[] args) {
//        UserDaoImpl userDao = new UserDaoImpl();
//
//        User user = new User("Add", "Raven", "17876", "tata@mail.com", "vice city", Role.ADMIN,
//                new Timestamp(System.currentTimeMillis()));
//
//        //userDao.deleteUser(17);
//        //userDao.insert(user);
//
//        userDao.deleteUser(20);
//        userDao.factory.close();
//        user.setId(16);
//        user.setLogin("Raven666");
//        user.setPassword("12345");
//        user.setRole(Role.USER);
//
//        userDao.update(user);
//
//         userDao.insert(user);
//
//        for (User user : userDao.getAll()) {
//            System.out.println(user);
//        }
//    }

    public void deleteUser(int id) {
        deletePursesOfUser(id);
        delete(id);
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
    public void delete(int id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            User user = new User();
            user.setId(id);
            session.delete(user);

            session.getTransaction().commit();
        }
    }

    @Override
    public void deletePursesOfUser(int id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            Query query = session.createQuery("delete from Purse where user.id=:id");
            query.setParameter("id", id);
            query.executeUpdate();

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
}
