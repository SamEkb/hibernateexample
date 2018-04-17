package ru.skilanov.controller.users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет главной страницы приложения.
 */
public class MainPageServlet extends HttpServlet {

    /**
     * JSP главной страницы.
     */
    private static final String MAIN_PAGE = "/WEB-INF/view//index.jsp";

    /**
     * Гет метод отвечающий за запрос главной страницы.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException ServletException
     * @throws IOException      IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(MAIN_PAGE).forward(req, resp);
    }
}
