package ru.skilanov.controller.users;

import ru.skilanov.dao.UserDaoImpl;
import ru.skilanov.model.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет отвечающий за удаление пользователя.
 */
public class DeleteUserServlet extends HttpServlet {
    /**
     * Константа дао пользователей.
     */
    private static final String USER_DAO = "userDao";
    /**
     * Список всех пользователей страница.
     */
    private static final String USERS_LIST = "usersList";
    /**
     * Дао пользователя.
     */
    private UserDaoImpl userDao;

    /**
     * Метод инициализации.
     */
    @Override
    public void init() {
        userDao = (UserDaoImpl) getServletContext().getAttribute(USER_DAO);
    }

    /**
     * Пост метод отвечающий за удаление клиентом пользователя.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws IOException IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter(User.ID));

        userDao.deleteUser(id);

        resp.sendRedirect(USERS_LIST);
    }
}
