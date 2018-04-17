package ru.skilanov.controller.purses;

import ru.skilanov.dao.CurrencyDao;
import ru.skilanov.dao.PurseDao;
import ru.skilanov.model.Currency;
import ru.skilanov.model.Purse;
import ru.skilanov.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * Сервлет отвечающий за добавление кошелька.
 */
public class AddPurseServlet extends HttpServlet {
    /**
     * JSP страница добавления кошелька.
     */
    private static final String ADD_PURSE = "WEB-INF/view/addPurse.jsp";
    /**
     * Дао кошелька.
     */
    private PurseDao purseDao;
    /**
     * Дао валюты.
     */
    private CurrencyDao currencyDao;

    /**
     * Метод инициализации.
     */
    @Override
    public void init() {
        purseDao = (PurseDao) getServletContext().getAttribute("purseDao");
        currencyDao = (CurrencyDao) getServletContext().getAttribute("currencyDao");
    }

    /**
     * Гет метод отвечающий за запрос страницы добавления кошелька.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException ServletException
     * @throws IOException      IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Currency> currencies = currencyDao.getAll();

        req.setAttribute("currencies", currencies);

        req.getRequestDispatcher(ADD_PURSE).forward(req, resp);
    }

    /**
     * Пост метод отвечающий за добавление клиентом кошелька.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws IOException IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String amount = req.getParameter("amount");
        String currencyId = req.getParameter("currency");
        Currency currency = currencyDao.findById(Integer.parseInt(currencyId));

        Purse purse = new Purse(user, currency, new BigDecimal(amount), new Timestamp(System.currentTimeMillis()));

        purseDao.insert(purse);

        resp.sendRedirect("purses");
    }
}
