package com.epam.balaian.hibernate.dao;

import com.epam.balaian.hibernate.model.Role;

/**
 * @author Vardan Balaian
 * @created 1/24/2020
 * @since 1.8
 */
public interface RoleDAO {

  Role getRoleById(int roleId);
}
