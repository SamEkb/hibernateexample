package ru.skilanov.controller.currencies;

import ru.skilanov.dao.CurrencyDao;
import ru.skilanov.model.Currency;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AllCurrencyServlet extends HttpServlet {
    private CurrencyDao currencyDao;

    @Override
    public void init() {
        currencyDao = (CurrencyDao) getServletContext().getAttribute("currencyDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Currency> currencies = currencyDao.getAll();

        req.setAttribute("currencies", currencies);

        req.getRequestDispatcher("WEB-INF/view/currencyList.jsp").forward(req, resp);
    }
}
