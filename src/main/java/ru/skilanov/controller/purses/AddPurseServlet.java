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
     * Константа дао кошельков.
     */
    private static final String PURSE_DAO = "purseDao";
    /**
     * Константа дао валют.
     */
    private static final String CURRENCY_DAO = "currencyDao";
    /**
     * Атрибут.
     */
    private static final String CURRENCIES = "currencies";
    /**
     * Jsp страница всех кошельков.
     */
    private static final String PURSES = "purses";
    /**
     * Дао кошелька.
     */
    private PurseDao purseDao;
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
     * Сеттер.
     *
     * @param purseDao PurseDao
     */
    public void setPurseDao(PurseDao purseDao) {
        this.purseDao = purseDao;
    }

    /**
     * Метод инициализации.
     */
    @Override
    public void init() {
        purseDao = (PurseDao) getServletContext().getAttribute(PURSE_DAO);
        currencyDao = (CurrencyDao) getServletContext().getAttribute(CURRENCY_DAO);
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

        req.setAttribute(CURRENCIES, currencies);

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
        User user = (User) session.getAttribute(Purse.USER);
        String amount = req.getParameter(Purse.AMOUNT);
        String currencyId = req.getParameter(Purse.CURRENCY);
        Currency currency = currencyDao.findById(Integer.parseInt(currencyId));

        Purse purse = new Purse(user, currency, new BigDecimal(amount), new Timestamp(System.currentTimeMillis()));

        purseDao.insert(purse);

        resp.sendRedirect(PURSES);
    }
}
