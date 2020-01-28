package com.epam.balaian.hibernate.dao;

import com.epam.balaian.hibernate.model.User;
import com.epam.balaian.hibernate.services.SessionTerminal;

/**
 * @author Vardan Balaian
 * @created 1/24/2020
 * @since 1.8
 */
public class UserDAOImpl implements UserDAO {

  @Override
  public User addUser(User user) {
    SessionTerminal.factory.getCurrentSession().save(user);
    return user;
  }

  @Override
  public User getUserById(long userId) {
    return (User) SessionTerminal.factory.getCurrentSession().get(User.class, userId);
  }
}
