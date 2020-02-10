package com.epam.balaian.hibernate.services;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Vardan Balaian
 * @created 28.01.2020
 * @since 1.8
 */
public class SessionTerminal {

  public static final SessionFactory FACTORY;

  static {
    FACTORY = new Configuration().configure().buildSessionFactory();
  }

  public static void openSessionAndTransaction() {
    FACTORY.openSession();
    FACTORY.getCurrentSession().beginTransaction();
  }

  public static void closeSessionAndTransaction() {
    FACTORY.getCurrentSession().getTransaction().commit();
    FACTORY.getCurrentSession().close();
  }
}
