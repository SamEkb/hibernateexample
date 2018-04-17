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
 * Сервлет отвечающий за регистрацию пользователей.
 */
public class RegisterUserServlet extends HttpServlet {
    /**
     * JSP страница регистрации.
     */
    private static final String REGISTER = "WEB-INF/view/userRegister.jsp";
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
     * Гет метод отвечающий за запрос страницы регистрации.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException ServletException
     * @throws IOException      IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(REGISTER).forward(req, resp);
    }

    /**
     * Пост метод отвечающий за регистрацию клиентом нового пользователя.
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
        Role role = Role.USER;
        String email = req.getParameter("email");

        User user = new User(name, login, password, email, role, new Timestamp(System.currentTimeMillis()));

        userDao.insert(user);

        resp.sendRedirect("/");
    }
}
