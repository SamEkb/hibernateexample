package ru.skilanov.controller.users;

import ru.skilanov.dao.UserDaoImpl;
import ru.skilanov.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Сервлет списка всех пользователей
 */
public class AllUsersServlet extends HttpServlet {
    /**
     * JSP страница всех пользователей.
     */
    private static final String ALL_USERS = "/WEB-INF/view//usersList.jsp";
    /**
     * Константа дао пользователей.
     */
    private static final String USER_DAO = "userDao";
    /**
     * Атрибут.
     */
    private static final String USERS = "users";
    /**
     * Дао пользователя.
     */
    private UserDaoImpl dao;

    @Override
    public void init() {
        dao = (UserDaoImpl) getServletContext().getAttribute(USER_DAO);
    }

    /**
     * Гет метод для запроса страницы всех пользователей.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException ServletException
     * @throws IOException      IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = dao.getAll();

        req.getServletContext().setAttribute(USERS, users);

        req.getRequestDispatcher(ALL_USERS).forward(req, resp);
    }
}
