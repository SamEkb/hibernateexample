package ru.skilanov.controller.purses;

import ru.skilanov.dao.PurseDao;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет отвечающий за удаление кошелька.
 */
public class DeletePurseServlet extends HttpServlet {
    /**
     * Дао кошелька.
     */
    private PurseDao purseDao;

    /**
     * Метод инициализации.
     */
    @Override
    public void init() {
        purseDao = (PurseDao) getServletContext().getAttribute("purseDao");
    }

    /**
     * Пост метод отвечающий за удаление клиентом кошелька.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws IOException IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        purseDao.delete(id);

        resp.sendRedirect("purses");
    }
}
