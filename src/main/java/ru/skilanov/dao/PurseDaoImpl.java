package ru.skilanov.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.skilanov.model.Purse;

import java.util.List;

public class PurseDaoImpl implements PurseDao {

    private SessionFactory factory;

    public PurseDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

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
        }
    }

    public void delete(int id) {
        try (Session session = factory.openSession()) {

            session.beginTransaction();

            Purse purse = new Purse();
            purse.setId(id);
            session.delete(purse);

            session.getTransaction().commit();
        }
    }

    public void update(Purse purse) {
        try (Session session = factory.openSession()) {

            session.beginTransaction();

            session.update(purse);

            session.getTransaction().commit();
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
