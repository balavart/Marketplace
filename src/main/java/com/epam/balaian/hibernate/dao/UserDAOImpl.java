package com.epam.balaian.hibernate.dao;

import com.epam.balaian.hibernate.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * @author Vardan Balaian
 * @created 1/24/2020
 * @since 1.8
 */
public class UserDAOImpl implements UserDAO {
  private final SessionFactory factory;

  public UserDAOImpl() {
    this.factory = new Configuration().configure().buildSessionFactory();
  }

  @Override
  public void addUser(User user) {
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
  public User getUserById(long userId) {
    final Session session = factory.openSession();
    Transaction tx = session.beginTransaction();
    try {
      return (User) session.get(User.class, userId);
    } finally {
      tx.commit();
      session.close();
    }
  }
}
