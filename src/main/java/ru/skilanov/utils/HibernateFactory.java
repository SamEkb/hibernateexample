package ru.skilanov.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateFactory {
    private static SessionFactory factory = new Configuration().configure().buildSessionFactory();

    public HibernateFactory() {
    }

    public static SessionFactory getFactory() {
        return factory;
    }
}
