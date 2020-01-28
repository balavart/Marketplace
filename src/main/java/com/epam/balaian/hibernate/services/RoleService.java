package com.epam.balaian.hibernate.services;

import com.epam.balaian.hibernate.dao.RoleDAO;
import com.epam.balaian.hibernate.model.Role;

/**
 * @author Vardan Balaian
 * @created 28.01.2020
 * @since 1.8
 */
public class RoleService {

  private RoleDAO roleDAO;

  public RoleService(RoleDAO roleDAO) {
    this.roleDAO = roleDAO;
  }

  public boolean checkRolePresence(Role role) {
    Role existingRole = roleDAO.getRoleById(role.getRoleId());

    return existingRole != null;
  }
}
