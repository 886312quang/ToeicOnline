package org.myproject.core.common.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory SessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create sessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable e) {
            System.out.println("Initial session factory fail!");
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return SessionFactory;
    }
}
