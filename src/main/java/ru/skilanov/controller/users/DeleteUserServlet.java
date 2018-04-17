package ru.skilanov.controller.users;

import ru.skilanov.dao.UserDaoImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет отвечающий за удаление пользователя.
 */
public class DeleteUserServlet extends HttpServlet {
    /**
     * Дао пользователя.
     */
    private UserDaoImpl userDao;

    /**
     * Метод инициализации.
     */
    @Override
    public void init() {
        userDao = (UserDaoImpl) getServletContext().getAttribute("userDao");
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
        int id = Integer.parseInt(req.getParameter("id"));

        userDao.deleteUser(id);

        resp.sendRedirect("usersList");
    }
}
