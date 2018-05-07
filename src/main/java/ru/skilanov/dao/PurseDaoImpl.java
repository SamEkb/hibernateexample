package ru.skilanov.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.skilanov.model.Purse;

import java.util.List;

/**
 * реализация интерфейса PurseDao.
 */
public class PurseDaoImpl implements PurseDao {

    /**
     * Фабрика сессий hibernate.
     */
    private SessionFactory factory;

    /**
     * Конструктор.
     * @param factory SessionFactory
     */
    public PurseDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    /**
     * Метод возвращает список всех кошельков.
     *
     * @return List
     */
    @Override
    public List<Purse> getAll() {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            List<Purse> list = session.createQuery("FROM Purse").list();

            session.getTransaction().commit();

            return list;
        }
    }

    /**
     * Метод добавления нового кошелька.
     *
     * @param purse Purse
     */
    @Override
    public void insert(Purse purse) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.save(purse);

            session.getTransaction().commit();
        }
    }

    /**
     * Метод удаления кошелька.
     *
     * @param id int
     */
    @Override
    public void delete(int id) {
        try (Session session = factory.openSession()) {

            session.beginTransaction();

            Purse purse = new Purse();
            purse.setId(id);
            session.delete(purse);

            session.getTransaction().commit();
        }
    }

    /**
     * Метод редактирования кошелька.
     *
     * @param purse Purse
     */
    @Override
    public void update(Purse purse) {
        try (Session session = factory.openSession()) {

            session.beginTransaction();

            session.update(purse);

            session.getTransaction().commit();
        }
    }

    /**
     * Метод поиска по id
     *
     * @param id int
     * @return Purse
     */
    @Override
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

    /**
     * Метод возвращает все кошельки пользователя.
     *
     * @param id int
     * @return List
     */
    @Override
    public List<Purse> getAllUserPurses(int id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            Query query = session.createQuery("from Purse where user.id=:id");
            query.setParameter(Purse.ID, id);

            List<Purse> list = query.list();

            session.getTransaction().commit();

            return list;
        }
    }
}
