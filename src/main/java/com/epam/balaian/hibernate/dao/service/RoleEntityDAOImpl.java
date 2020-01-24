package com.epam.balaian.hibernate.dao.service;

import com.epam.balaian.hibernate.dao.RoleEntityDAO;
import com.epam.balaian.hibernate.model.RoleEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * @author Vardan Balaian
 * @created 1/24/2020
 * @since 1.8
 */
public class RoleEntityDAOImpl implements RoleEntityDAO {
  private final SessionFactory factory;

  public RoleEntityDAOImpl() {
    this.factory = new Configuration().configure().buildSessionFactory();
  }

  @Override
  public RoleEntity getRoleById(int roleId) {
    final Session session = factory.openSession();
    Transaction tx = session.beginTransaction();
    try {
      return (RoleEntity) session.get(RoleEntity.class, roleId);
    } finally {
      tx.commit();
      session.close();
    }
  }
}
