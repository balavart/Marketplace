package com.epam.balaian.hibernate.services;

import com.epam.balaian.hibernate.dao.UserDAO;
import com.epam.balaian.hibernate.model.User;

/**
 * @author Vardan Balaian
 * @created 26.01.2020
 * @since 1.8
 */
public class UserService {

  private UserDAO userDAO;

  public UserService(UserDAO userDAO) {
    this.userDAO = userDAO;
  }

  public boolean checkUserAddition(User user) {
    User addedUser = userDAO.addUser(user);

    return addedUser != null;
  }

  public boolean checkUserPresence(User user) {
    User existingUser = userDAO.getUserById(user.getUserId());

    return existingUser != null;
  }
}
