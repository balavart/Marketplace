package com.epam.balaian.hibernate.dao;

import com.epam.balaian.hibernate.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * @author Vardan Balaian
 * @created 1/24/2020
 * @since 1.8
 */
public class RoleDAOImpl implements RoleDAO {
  private final SessionFactory factory;

  public RoleDAOImpl() {
    this.factory = new Configuration().configure().buildSessionFactory();
  }

  @Override
  public Role getRoleById(int roleId) {
    final Session session = factory.openSession();
    Transaction tx = session.beginTransaction();
    try {
      return (Role) session.get(Role.class, roleId);
    } finally {
      tx.commit();
      session.close();
    }
  }
}
