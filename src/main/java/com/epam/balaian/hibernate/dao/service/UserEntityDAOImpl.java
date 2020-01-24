package com.epam.balaian.hibernate.dao.service;

import com.epam.balaian.hibernate.dao.UserEntityDAO;
import com.epam.balaian.hibernate.model.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * @author Vardan_Balaian
 * @created 1/24/2020
 * @since 1.8
 */
public class UserEntityDAOImpl implements UserEntityDAO {
  private final SessionFactory factory;

  public UserEntityDAOImpl() {
    this.factory = new Configuration().configure().buildSessionFactory();
  }

  @Override
  public void addUser(final UserEntity user) {
    final Session session = factory.openSession();
    Transaction tx = session.beginTransaction();

    try {
      session.save(user);
    } finally {
      tx.commit();
      session.close();
    }
  }

  @Override
  public UserEntity getUserById(long userId) {
    final Session session = factory.openSession();
    Transaction tx = session.beginTransaction();
    try {
      return (UserEntity) session.get(UserEntity.class, userId);
    } finally {
      tx.commit();
      session.close();
    }
  }
}
