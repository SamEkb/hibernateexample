package ru.skilanov.dao;

import ru.skilanov.model.Currency;

import java.util.List;

public interface CurrencyDao {
    List<Currency> getAll();

    void insert(Currency currency);

    void deleteCurrency(int id);

    void update(Currency currency);

    Currency findById(int id);
}
