package ru.skilanov.controller.currencies;

import ru.skilanov.dao.CurrencyDao;
import ru.skilanov.model.Currency;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет отвечающий за изменение валюты.
 */
public class UpdateCurrencyServlet extends HttpServlet {
    /**
     * JSP страница обновления валюты.
     */
    private static final String UPDATE_CURRENCY = "/WEB-INF/view//updateCurrency.jsp";
    /**
     * Дао валюты.
     */
    private CurrencyDao currencyDao;

    /**
     * Метод инициализации.
     */
    @Override
    public void init() {
        currencyDao = (CurrencyDao) getServletContext().getAttribute("currencyDao");
    }

    /**
     * Гет метод отвечающий за запрос страницы обновления валюты.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException ServletException
     * @throws IOException      IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Currency currency = currencyDao.findById(id);

        req.setAttribute("currency", currency);

        req.getRequestDispatcher(UPDATE_CURRENCY).forward(req, resp);
    }

    /**
     * Пост метод отвечающий за измененеи клиентом валюты.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws IOException IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");

        Currency currency = currencyDao.findById(id);
        currency.setName(name);

        currencyDao.update(currency);

        resp.sendRedirect("/currencyList");
    }
}
