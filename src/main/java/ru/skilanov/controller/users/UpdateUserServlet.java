package ru.skilanov.controller.users;

import ru.skilanov.dao.UserDaoImpl;
import ru.skilanov.model.Role;
import ru.skilanov.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет отвечающий за обновление данных пользователя.
 */
public class UpdateUserServlet extends HttpServlet {
    /**
     * JSP страница обновления.
     */
    private static final String UPDATE_USER = "/WEB-INF/view//updateUser.jsp";
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
     * Гет метод отвечающий за запрос страницы обновления пользователя.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException ServletException
     * @throws IOException      IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        User user = userDao.findById(id);

        req.setAttribute("user", user);

        req.getRequestDispatcher(UPDATE_USER).forward(req, resp);
    }

    /**
     * Пост метод отвечающий за обновление клиентом данных.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws IOException IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String city = req.getParameter("city");
        Role role = Role.getRole(req.getParameter("role"));

        User user = userDao.findById(id);
        user.setName(name);
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
        user.setCity(city);
        user.setRole(role);

        userDao.update(user);

        resp.sendRedirect("usersList");
    }
}
