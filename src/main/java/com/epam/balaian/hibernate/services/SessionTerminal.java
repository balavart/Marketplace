package com.epam.balaian.hibernate.services;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Vardan Balaian
 * @created 28.01.2020
 * @since 1.8
 */
public class SessionTerminal {

  public static final SessionFactory factory;

  static {
    factory = new Configuration().configure().buildSessionFactory();
  }

  public static void openSessionAndTransaction() {
    factory.openSession();
    factory.getCurrentSession().beginTransaction();
  }

  public static void closeSessionAndTransaction() {
    factory.getCurrentSession().getTransaction().commit();
    factory.getCurrentSession().close();
  }
}
