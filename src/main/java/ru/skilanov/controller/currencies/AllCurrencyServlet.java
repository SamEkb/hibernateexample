package ru.skilanov.controller.currencies;

import ru.skilanov.dao.CurrencyDao;
import ru.skilanov.model.Currency;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Сервлет отвечает за отображение списка валют.
 */
public class AllCurrencyServlet extends HttpServlet {
    /**
     * JSP страница списка валют.
     */
    private static final String CURRENCIES_LIST = "WEB-INF/view/currencyList.jsp";
    /**
     * Атрибут.
     */
    private static final String CURRENCIES = "currencies";
    /**
     * Константа дао валют.
     */
    private static final String CURRENCY_DAO = "currencyDao";
    /**
     * Дао валюты.
     */
    private CurrencyDao currencyDao;

    /**
     * Метод инициализации.
     */
    @Override
    public void init() {
        currencyDao = (CurrencyDao) getServletContext().getAttribute(CURRENCY_DAO);
    }

    /**
     * гет метод отвечающий за запрос страницы всех валют.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException ServletException
     * @throws IOException      IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Currency> currencies = currencyDao.getAll();

        req.setAttribute(CURRENCIES, currencies);

        req.getRequestDispatcher(CURRENCIES_LIST).forward(req, resp);
    }
}
