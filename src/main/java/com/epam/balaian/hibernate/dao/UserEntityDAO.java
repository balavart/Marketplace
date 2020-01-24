package com.epam.balaian.hibernate.dao;

import com.epam.balaian.hibernate.model.UserEntity;

/**
 * @author Vardan_Balaian
 * @created 1/24/2020
 * @since 1.8
 */
public interface UserEntityDAO {

  void addUser(final UserEntity user);

  UserEntity getUserById(long userId);
}
