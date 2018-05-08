package ru.skilanov.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;
import ru.skilanov.model.Currency;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Класс тестирует dao валют.
 */
public class CurrencyDaoImplTest {

    /**
     * Константа наименования валюты рубль.
     */
    private static final String RUR = "rur";
    /**
     * Константа наименования валюты доллар США.
     */
    private static final String USD = "usd";
    /**
     * Константа 1.
     */
    private static final int ONE = 1;
    /**
     * Константа 0.
     */
    private static final int ZERO = 0;
    /**
     * Константа наименования измененной валюты рубль.
     */
    private static final String RUB = "rub";
    /**
     * Константа 2.
     */
    private static final int TWO = 2;
    /**
     * Dao валют.
     */
    private CurrencyDao currencyDao;

    /**
     * Перед тестом.
     *
     * @throws Exception Exception
     */
    @Before
    public void setUp() throws Exception {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        currencyDao = new CurrencyDaoImpl(factory);
    }

    /**
     * Тестирует удаление валюты.
     */
    @Test
    public void whenDeleteCurrency_ThenItDeleted() {
        Currency rurCurrency = new Currency(RUR);

        currencyDao.insert(rurCurrency);
        currencyDao.deleteCurrency(ONE);

        assertNull(currencyDao.findById(ONE));
    }

    /**
     * Тестирует получение всех валют.
     */
    @Test
    public void whnGetAllCurrencies_ThenReturnList() {
        Currency rurCurrency = new Currency(RUR);
        Currency usdCurrency = new Currency(USD);

        currencyDao.insert(rurCurrency);
        currencyDao.insert(usdCurrency);

        int expectedSize = currencyDao.getAll().size();

        assertThat(expectedSize, is(TWO));
    }

    /**
     * Тестирует добавление новой валюты.
     */
    @Test
    public void whenInsertNewCurrency_ThenItInserted() {
        Currency rurCurrency = new Currency(RUR);

        currencyDao.insert(rurCurrency);

        assertNotNull(currencyDao.getAll());
    }

    /**
     * Тестирует изменение валюты.
     */
    @Test
    public void whenUpdateCurrency_ThenItUpdated() {
        Currency rurCurrency = new Currency(RUR);

        currencyDao.insert(rurCurrency);

        Currency currency = currencyDao.findById(ONE);
        currency.setName(RUB);
        currencyDao.update(currency);

        String expectedName = currencyDao.getAll().get(ZERO).getName();

        assertThat(expectedName, is(RUB));
    }

    /**
     * Тестирует поиск по id.
     */
    @Test
    public void whenFindCurrencyById_ThenReturnRightResult() {
        Currency rurCurrency = new Currency(RUR);

        currencyDao.insert(rurCurrency);

        Currency expectedCurrency = currencyDao.findById(ONE);

        assertThat(expectedCurrency, is(rurCurrency));
    }
}
