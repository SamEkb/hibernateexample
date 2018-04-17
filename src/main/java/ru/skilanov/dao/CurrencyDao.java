package ru.skilanov.dao;

import ru.skilanov.model.Currency;

import java.util.List;

/**
 * Дао интерфейс валюты.
 */
public interface CurrencyDao {
    /**
     * Метод возвращает список всех валют.
     *
     * @return List
     */
    List<Currency> getAll();

    /**
     * Метод добавляет новую валюту.
     *
     * @param currency Currency
     */
    void insert(Currency currency);

    /**
     * Метод удаляет валюту.
     *
     * @param id nt
     */
    void deleteCurrency(int id);

    /**
     * Метод обновления валюты.
     *
     * @param currency Currency
     */
    void update(Currency currency);

    /**
     * Метод поиска валюты по id.
     *
     * @param id int
     * @return Currency
     */
    Currency findById(int id);
}
