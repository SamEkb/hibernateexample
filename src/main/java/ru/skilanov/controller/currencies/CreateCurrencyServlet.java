package ru.skilanov.controller.currencies;

import ru.skilanov.dao.CurrencyDao;
import ru.skilanov.model.Currency;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет отвечающий за создание новой валюты.
 */
public class CreateCurrencyServlet extends HttpServlet {
    /**
     * JSP страница добавления валюты.
     */
    private static final String ADD_CURRENCY = "WEB-INF/view/addCurrency.jsp";
    /**
     * Константа дао валют.
     */
    private static final String CURRENCY_DAO = "currencyDao";
    /**
     * Jsp страница списка всех валют.
     */
    private static final String CURRENCY_LIST = "currencyList";
    /**
     * Дао валют.
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
     *
     * @throws ServletException ServletException
     */
    @Override
    public void init() throws ServletException {
        currencyDao = (CurrencyDao) getServletContext().getAttribute(CURRENCY_DAO);
    }

    /**
     * Гет метод отвечающий за запрос страницы добавления валюты.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException ServletException
     * @throws IOException      IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(ADD_CURRENCY).forward(req, resp);
    }

    /**
     * Пост метод отвечающий за ввод клинтом новой валюты.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws IOException IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter(Currency.NAME);

        Currency currency = new Currency();
        currency.setName(name);

        currencyDao.insert(currency);

        resp.sendRedirect(CURRENCY_LIST);
    }
}
