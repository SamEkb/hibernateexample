package ru.skilanov.controller.purses;

import ru.skilanov.dao.PurseDao;
import ru.skilanov.dao.UserDao;
import ru.skilanov.model.Purse;
import ru.skilanov.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserPursesServlet extends HttpServlet {
    private PurseDao purseDao;
    private UserDao userDao;

    @Override
    public void init() {
        purseDao = (PurseDao) getServletContext().getAttribute("purseDao");
        userDao = (UserDao) getServletContext().getAttribute("userDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        User user = userDao.findById(id);

        req.setAttribute("user", user);

        req.getRequestDispatcher("/WEB-INF/view//userPurses.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        List<Purse> purses = purseDao.getAllUserPurses(id);
        req.setAttribute("purses", purses);

        req.getRequestDispatcher("/WEB-INF/view//userPurses.jsp").forward(req, resp);
    }
}
