package ru.skilanov.controller.currencies;

import ru.skilanov.dao.CurrencyDao;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет отвечающий за удаление валюты.
 */
public class DeleteCurrencyServlet extends HttpServlet {
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
     * Пост метод отвечающий за удаление клиентом валюты.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws IOException IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        currencyDao.deleteCurrency(id);

        resp.sendRedirect("currencyList");
    }
}
