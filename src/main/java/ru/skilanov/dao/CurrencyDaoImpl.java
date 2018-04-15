package ru.skilanov.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.skilanov.model.Currency;
import ru.skilanov.utils.HibernateFactory;

import java.util.List;

public class CurrencyDaoImpl implements CurrencyDao {

    private SessionFactory factory = HibernateFactory.getFactory();

//    public static void main(String[] args) {
//        CurrencyDaoImpl currencyDao = new CurrencyDaoImpl();
//
//        currencyDao.delete(15);
//
//        System.out.println(currencyDao.findById(14));
//
//        Currency currency = new Currency();
//        currency.setId(15);
//        currency.setName("Rur");
//
//        currencyDao.update(currency);
//
//        Currency currency = new Currency("Rub");
//        currencyDao.insert(currency);
//
//        for (Currency currency : currencyDao.getAll()) {
//            System.out.println(currency);
//        }
//
//        currencyDao.deleteCurrency(17);
//    }

    public void deleteCurrency(int id){
        deletePursesOfCurrency(id);
        delete(id);
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
    public void delete(int id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            Currency currency = new Currency();
            currency.setId(id);
            session.delete(currency);

            session.getTransaction().commit();
        }
    }


    @Override
    public void deletePursesOfCurrency(int id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            Query query = session.createQuery("delete from Purse where currency.id=:id");
            query.setParameter("id", id);
            query.executeUpdate();

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
