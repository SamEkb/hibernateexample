package ru.skilanov.controller.purses;

import ru.skilanov.dao.PurseDao;
import ru.skilanov.dao.UserDao;
import ru.skilanov.model.Purse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Севрлет отвечает за список кошельков пользователя.
 */
public class UserPursesServlet extends HttpServlet {
    /**
     * JSP страница кошельков пользователя.
     */
    private static final String USER_PURSES = "/WEB-INF/view//userPurses.jsp";
    /**
     * Дао кошелька.
     */
    private PurseDao purseDao;

    /**
     * Дао пользователя.
     */
    private UserDao userDao;

    /**
     * Метод инициализации.
     */
    @Override
    public void init() {
        purseDao = (PurseDao) getServletContext().getAttribute("purseDao");
        userDao = (UserDao) getServletContext().getAttribute("userDao");
    }

    /**
     * Гет метод.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException ServletException
     * @throws IOException      IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(USER_PURSES).forward(req, resp);
    }

    /**
     * Пост метод получаения всех кошельков.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException ServletException
     * @throws IOException      IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        List<Purse> purses = purseDao.getAllUserPurses(id);
        req.setAttribute("purses", purses);

        req.getRequestDispatcher(USER_PURSES).forward(req, resp);
    }
}
