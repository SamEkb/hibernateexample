package ru.skilanov.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.skilanov.model.Currency;
import ru.skilanov.model.Purse;
import ru.skilanov.model.Role;
import ru.skilanov.model.User;
import ru.skilanov.utils.HibernateFactory;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class PurseDaoImpl implements PurseDao {

   private SessionFactory factory = HibernateFactory.getFactory();

//    public static void main(String[] args) {
//        PurseDaoImpl dao = new PurseDaoImpl();
//
//        Currency currency = new Currency("usd");
//        Role role = Role.getRole("user");
//        User user = new User("Sam", "login", "pass", "email", "city", role,
//                new Timestamp(System.currentTimeMillis()));
//        Purse purse = new Purse(user, currency, new BigDecimal(100.67), new Timestamp(System.currentTimeMillis()));
//        dao.insert(purse);
//        dao.factory.close();
//        for (Purse purse1 : dao.getAll()) {
//            System.out.println(purse1);
//        }
//
//        purse.setId(1);
//        purse.setCurrency(new Currency("euro"));
//        purse.setUser(new User("Dan", "user1990", "passick", "mail.com", "miami",
//                role, new Timestamp(System.currentTimeMillis())));
//        purse.setAmount(new BigDecimal(10000));
//        purse.setCreateDate(new Timestamp(System.currentTimeMillis()));
//        dao.update(purse);
//        dao.delete(8);
//    }

    public List<Purse> getAll() {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            List<Purse> list = session.createQuery("FROM Purse").list();

            session.getTransaction().commit();

            return list;
        }
    }

    public void insert(Purse purse) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.save(purse);

            session.getTransaction().commit();
            factory.close();
        }
    }

    public void delete(int id) {
        try (Session session = factory.openSession()) {

            session.beginTransaction();

            Purse purse = new Purse();
            purse.setId(id);
            session.delete(purse);

            session.getTransaction().commit();
            factory.close();
        }
    }

    public void update(Purse purse) {
        try (Session session = factory.openSession()) {

            session.beginTransaction();

            session.update(purse);

            session.getTransaction().commit();
            factory.close();
        }
    }

    public Purse findById(int id) {
        try (Session session = factory.openSession()) {

            session.beginTransaction();

            Purse result = session.get(Purse.class, id);

            if (result != null) {
                Hibernate.initialize(result.getUser());
                Hibernate.initialize(result.getCurrency());
            }

            session.getTransaction().commit();
            return result;
        }
    }
}
