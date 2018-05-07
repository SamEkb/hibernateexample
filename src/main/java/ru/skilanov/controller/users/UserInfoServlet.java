package ru.skilanov.controller.users;

import ru.skilanov.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Сервлет отвечающий за страницы информации о пользователе.
 */
public class UserInfoServlet extends HttpServlet {

    /**
     * JSP страница с информацие о пользователе.
     */
    private static final String USER_INFO = "WEB-INF/view/userInfoPage.jsp";
    /**
     * Атрибут.
     */
    private static final String USER = "user";

    /**
     * гет метод отображает страницу информации о пользователе.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException ServletException
     * @throws IOException      IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(USER);

        req.setAttribute(USER, user);

        req.getRequestDispatcher(USER_INFO).forward(req, resp);
    }
}
