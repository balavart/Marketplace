package com.epam.balaian.hibernate.dao.service;

import com.epam.balaian.hibernate.dao.StatusTypeEntityDAO;
import com.epam.balaian.hibernate.model.StatusTypeEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * @author Vardan Balaian
 * @created 1/24/2020
 * @since 1.8
 */
public class StatusTypeEntityDAOImpl implements StatusTypeEntityDAO {
  private final SessionFactory factory;

  public StatusTypeEntityDAOImpl() {
    this.factory = new Configuration().configure().buildSessionFactory();
  }

  @Override
  public StatusTypeEntity getStatusById(int statusId) {
    final Session session = factory.openSession();
    Transaction tx = session.beginTransaction();
    try {
      return (StatusTypeEntity) session.get(StatusTypeEntity.class, statusId);
    } finally {
      tx.commit();
      session.close();
    }
  }
}
