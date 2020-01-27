package com.epam.balaian.hibernate.dao;

import com.epam.balaian.hibernate.model.StatusType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * @author Vardan Balaian
 * @created 1/24/2020
 * @since 1.8
 */
public class StatusTypeDAOImpl implements StatusTypeDAO {
  private final SessionFactory factory;

  public StatusTypeDAOImpl() {
    this.factory = new Configuration().configure().buildSessionFactory();
  }

  @Override
  public StatusType getStatusById(int statusId) {
    final Session session = factory.openSession();
    Transaction tx = session.beginTransaction();
    try {
      return (StatusType) session.get(StatusType.class, statusId);
    } finally {
      tx.commit();
      session.close();
    }
  }
}
