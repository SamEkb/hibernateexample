package ru.skilanov.controller.users;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Сервлет выхода из сессии.
 */
public class LogoutServlet extends HttpServlet {
    /**
     * Гет метод отвечающий за выход из сессии.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws IOException IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();

        session.removeAttribute("user");

        resp.sendRedirect("/");
    }
}
