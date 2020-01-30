package com.epam.balaian.hibernate.dao.impl;

import com.epam.balaian.hibernate.dao.UserDAO;
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
    SessionTerminal.openSessionAndTransaction();

    try{
      SessionTerminal.FACTORY.getCurrentSession().save(user);
      return user;
    }finally{
      SessionTerminal.closeSessionAndTransaction();
    }
  }

  @Override
  public User getUserById(long userId) {
    SessionTerminal.openSessionAndTransaction();

    try{
      return SessionTerminal.FACTORY.getCurrentSession().get(User.class, userId);
    }finally{
      SessionTerminal.closeSessionAndTransaction();
    }
  }
}
