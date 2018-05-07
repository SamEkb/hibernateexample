package ru.skilanov.contextlistener;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.skilanov.dao.CurrencyDaoImpl;
import ru.skilanov.dao.PurseDaoImpl;
import ru.skilanov.dao.UserDaoImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Класс отвечает за инициализацию слоя дао в приложении.
 */
public class AppContextListener implements ServletContextListener {

    /**
     * Фабрика сессий hibernate.
     */
    private SessionFactory sessionFactory;

    /**
     * Метод инициализации.
     *
     * @param sce ServletContextEvent
     */
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

    /**
     * Метод закрывает ресурсы по завершению работы приложения.
     *
     * @param sce ServletContextEvent
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sessionFactory.close();
    }
}
