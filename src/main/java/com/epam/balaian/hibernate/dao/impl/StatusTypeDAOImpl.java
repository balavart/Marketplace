package com.epam.balaian.hibernate.dao.impl;

import com.epam.balaian.hibernate.dao.StatusTypeDAO;
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
    SessionTerminal.openSessionAndTransaction();

    try {
      return SessionTerminal.FACTORY.getCurrentSession().get(StatusType.class, statusId);
    } finally {
      SessionTerminal.closeSessionAndTransaction();
    }
  }

  @Override
  public StatusType getStatusByTitle(String statusTitle) {
    SessionTerminal.openSessionAndTransaction();

    try {
      return SessionTerminal.FACTORY
          .getCurrentSession()
          .createQuery("from StatusType where statusTitle =:status_title_param", StatusType.class)
          .setParameter("status_title_param", statusTitle)
          .uniqueResult();
    } finally {
      SessionTerminal.closeSessionAndTransaction();
    }
  }
}
