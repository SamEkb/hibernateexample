package ru.skilanov.controller.currencies;

import ru.skilanov.dao.CurrencyDao;
import ru.skilanov.model.Currency;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateCurrencyServlet extends HttpServlet {
    private CurrencyDao currencyDao;

    @Override
    public void init() {
        currencyDao = (CurrencyDao) getServletContext().getAttribute("currencyDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Currency currency = currencyDao.findById(id);

        req.setAttribute("currency", currency);

        req.getRequestDispatcher("/WEB-INF/view//updateCurrency.jsp").forward(req, resp);
    }

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
