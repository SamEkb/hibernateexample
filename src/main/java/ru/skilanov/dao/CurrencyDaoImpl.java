package ru.skilanov.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.skilanov.model.Currency;

import java.util.List;

public class CurrencyDaoImpl implements CurrencyDao {

    public CurrencyDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    private SessionFactory factory;

    @Override
    public void deleteCurrency(int id){
        deletePursesOfCurrency(id);
        delete(id);
    }


    private void delete(int id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            Currency currency = new Currency();
            currency.setId(id);
            session.delete(currency);

            session.getTransaction().commit();
        }
    }

    private void deletePursesOfCurrency(int id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            Query query = session.createQuery("delete from Purse where currency.id=:id");
            query.setParameter("id", id);
            query.executeUpdate();

            session.getTransaction().commit();
        }
    }

    @Override
    public List<Currency> getAll() {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            List<Currency> currencies = session.createQuery("FROM Currency ").list();

            session.getTransaction().commit();

            return currencies;
        }
    }

    @Override
    public void insert(Currency currency) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.save(currency);

            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Currency currency) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.update(currency);

            session.getTransaction().commit();
        }
    }

    @Override
    public Currency findById(int id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            Currency currency = session.get(Currency.class, id);

            session.getTransaction().commit();
            return currency;
        }
    }
}
