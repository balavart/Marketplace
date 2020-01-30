package com.epam.balaian.hibernate.dao.impl;

import com.epam.balaian.hibernate.dao.RoleDAO;
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
    SessionTerminal.openSessionAndTransaction();

    try{
      return SessionTerminal.FACTORY.getCurrentSession().get(Role.class, roleId);
    }finally{
      SessionTerminal.closeSessionAndTransaction();
    }
  }
}
