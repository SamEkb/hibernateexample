package ru.skilanov.controller.contextlistener;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.skilanov.dao.CurrencyDaoImpl;
import ru.skilanov.dao.PurseDaoImpl;
import ru.skilanov.dao.UserDaoImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppContextListener implements ServletContextListener {

    private SessionFactory sessionFactory;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        UserDaoImpl userDao = new UserDaoImpl(sessionFactory);
        PurseDaoImpl purseDao = new PurseDaoImpl(sessionFactory);
        CurrencyDaoImpl currencyDao = new CurrencyDaoImpl(sessionFactory);

        ServletContext context = sce.getServletContext();
        context.setAttribute("userDao", userDao);
        context.setAttribute("purseDao", purseDao);
        context.setAttribute("currencyDao", currencyDao);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sessionFactory.close();
    }
}
