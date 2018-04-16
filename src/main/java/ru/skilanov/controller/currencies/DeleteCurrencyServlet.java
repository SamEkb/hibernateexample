package ru.skilanov.controller.currencies;

import ru.skilanov.dao.CurrencyDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCurrencyServlet extends HttpServlet {
    private CurrencyDao currencyDao;

    @Override
    public void init() throws ServletException {
       currencyDao = (CurrencyDao) getServletContext().getAttribute("currencyDao");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        currencyDao.deleteCurrency(id);

        resp.sendRedirect("currencyList");
    }
}
