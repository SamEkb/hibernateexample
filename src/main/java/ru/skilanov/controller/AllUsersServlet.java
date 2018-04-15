package ru.skilanov.controller;

import ru.skilanov.dao.PurseDaoImpl;
import ru.skilanov.model.Purse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AllUsersServlet extends HttpServlet {
    private PurseDaoImpl dao;

    @Override
    public void init() throws ServletException {
        dao = (PurseDaoImpl) getServletContext().getAttribute("purseDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        List<Purse> purses = dao.getAll();

        req.getServletContext().setAttribute("purses", purses);

        req.getRequestDispatcher("/WEB-INF/view//list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
