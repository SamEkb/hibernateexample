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
     * Страница списка всех пользователей.
     */
    private static final String USERS_LIST = "usersList";
    /**
     * Константа дао пользователей.
     */
    private static final String USER_DAO = "userDao";
    /**
     * Дао пользователя.
     */
    private UserDao userDao;

    /**
     * Сеттер.
     *
     * @param userDao UserDao
     */
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Метод инициализации.
     */
    @Override
    public void init() {
        userDao = (UserDao) getServletContext().getAttribute(USER_DAO);
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
        String name = req.getParameter(User.NAME);
        String login = req.getParameter(User.LOGIN);
        String password = req.getParameter(User.PASSWORD);
        String email = req.getParameter(User.EMAIL);
        String city = req.getParameter(User.CITY);
        Role role = Role.getRole(req.getParameter(User.ROLE));

        User user = new User(name, login, password, email, city, role, new Timestamp(System.currentTimeMillis()));

        userDao.insert(user);

        resp.sendRedirect(USERS_LIST);
    }
}
