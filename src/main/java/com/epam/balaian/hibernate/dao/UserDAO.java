package com.epam.balaian.hibernate.dao;

import com.epam.balaian.hibernate.model.User;

/**
 * @author Vardan Balaian
 * @created 1/24/2020
 * @since 1.8
 */
public interface UserDAO {

  void addUser(User user);

  User getUserById(long userId);
}
