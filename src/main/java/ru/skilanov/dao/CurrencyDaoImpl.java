package ru.skilanov.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.skilanov.model.Currency;

import java.util.List;

/**
 * Реализация интерфейса CurrencyDao
 */
public class CurrencyDaoImpl implements CurrencyDao {

    /**
     * Фабрика сессий hibernate.
     */
    private SessionFactory factory;

    /**
     * Конструктор
     *
     * @param factory SessionFactory
     */
    public CurrencyDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    /**
     * Метод удаляет валюту.
     *
     * @param id int
     */
    @Override
    public void deleteCurrency(int id) {
        deletePursesOfCurrency(id);
        delete(id);
    }

    /**
     * Метод удаляет валюту.
     *
     * @param id int
     */
    private void delete(int id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            Currency currency = new Currency();
            currency.setId(id);
            session.delete(currency);

            session.getTransaction().commit();
        }
    }

    /**
     * Метод удаляет кошельки с валютой.
     *
     * @param id int
     */
    private void deletePursesOfCurrency(int id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            Query query = session.createQuery("delete from Purse where currency.id=:id");
            query.setParameter(Currency.ID, id);
            query.executeUpdate();

            session.getTransaction().commit();
        }
    }

    /**
     * Метод возвращает список всех валют.
     *
     * @return List
     */
    @Override
    public List<Currency> getAll() {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            List<Currency> currencies = session.createQuery("FROM Currency ").list();

            session.getTransaction().commit();

            return currencies;
        }
    }

    /**
     * Метод добавляет новую валюту.
     *
     * @param currency Currency
     */
    @Override
    public void insert(Currency currency) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.save(currency);

            session.getTransaction().commit();
        }
    }

    /**
     * Метод обновления валюты.
     *
     * @param currency Currency
     */
    @Override
    public void update(Currency currency) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.update(currency);

            session.getTransaction().commit();
        }
    }

    /**
     * Метод поиска валюты по id.
     *
     * @param id int
     * @return Currency
     */
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
