package ru.skilanov.controller.currencies;

import ru.skilanov.dao.CurrencyDao;
import ru.skilanov.model.Currency;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет отвечающий за удаление валюты.
 */
public class DeleteCurrencyServlet extends HttpServlet {
    /**
     * Константа дао валют.
     */
    private static final String CURRENCY_DAO = "currencyDao";
    /**
     * Jsp страница списка всех валют.
     */
    private static final String CURRENCY_LIST = "currencyList";
    /**
     * Дао валюты.
     */
    private CurrencyDao currencyDao;

    /**
     * Сеттер.
     *
     * @param currencyDao CurrencyDao
     */
    public void setCurrencyDao(CurrencyDao currencyDao) {
        this.currencyDao = currencyDao;
    }

    /**
     * Метод инициализации.
     */
    @Override
    public void init() {
        currencyDao = (CurrencyDao) getServletContext().getAttribute(CURRENCY_DAO);
    }

    /**
     * Пост метод отвечающий за удаление клиентом валюты.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws IOException IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter(Currency.ID));

        currencyDao.deleteCurrency(id);

        resp.sendRedirect(CURRENCY_LIST);
    }
}
