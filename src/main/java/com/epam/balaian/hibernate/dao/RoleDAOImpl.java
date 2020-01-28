package com.epam.balaian.hibernate.dao;

import com.epam.balaian.hibernate.model.Role;
import com.epam.balaian.hibernate.services.SessionTerminal;

/**
 * @author Vardan Balaian
 * @created 1/24/2020
 * @since 1.8
 */
public class RoleDAOImpl implements RoleDAO {

  @Override
  public Role getRoleById(int roleId) {
    return (Role) SessionTerminal.factory.getCurrentSession().get(Role.class, roleId);
  }
}
