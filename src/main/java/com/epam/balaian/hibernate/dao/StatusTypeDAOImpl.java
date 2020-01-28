package com.epam.balaian.hibernate.dao;

import com.epam.balaian.hibernate.model.StatusType;
import com.epam.balaian.hibernate.services.SessionTerminal;

/**
 * @author Vardan Balaian
 * @created 1/24/2020
 * @since 1.8
 */
public class StatusTypeDAOImpl implements StatusTypeDAO {

  @Override
  public StatusType getStatusById(int statusId) {
    return (StatusType) SessionTerminal.factory.getCurrentSession().get(StatusType.class, statusId);
  }
}
