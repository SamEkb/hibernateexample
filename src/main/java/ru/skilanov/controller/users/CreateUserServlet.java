package ru.skilanov.controller.users;

import ru.skilanov.dao.UserDao;
import ru.skilanov.model.Role;
import ru.skilanov.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

/**
 * Сервлет добавления нового пользователя.
 */
public class CreateUserServlet extends HttpServlet {
    /**
     * JSP страница добавления пользователя.
     */
    private static final String ADD_USER = "WEB-INF/view/addUser.jsp";
    /**
     * Дао пользователя.
     */
    private UserDao userDao;

    /**
     * Метод инициализации.
     */
    @Override
    public void init() {
        userDao = (UserDao) getServletContext().getAttribute("userDao");
    }

    /**
     * Гет метод отвечающий за запрос страницы добавления пользователя.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException ServletException
     * @throws IOException      IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(ADD_USER).forward(req, resp);
    }

    /**
     * Пост метод отвечающий за добавление клиентом новго пользователя.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws IOException IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String city = req.getParameter("city");
        Role role = Role.getRole(req.getParameter("role"));

        User user = new User(name, login, password, email, city, role, new Timestamp(System.currentTimeMillis()));

        userDao.insert(user);

        resp.sendRedirect("usersList");
    }
}
