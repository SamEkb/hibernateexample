package ru.skilanov.controller.currencies;

import ru.skilanov.dao.CurrencyDao;
import ru.skilanov.model.Currency;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateCurrencyServlet extends HttpServlet {
    private CurrencyDao currencyDao;

    @Override
    public void init() throws ServletException {
        currencyDao = (CurrencyDao) getServletContext().getAttribute("currencyDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/view/addCurrency.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");

        Currency currency = new Currency();
        currency.setName(name);

        currencyDao.insert(currency);

        resp.sendRedirect("currencyList");
    }
}
