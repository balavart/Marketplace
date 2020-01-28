package com.epam.balaian.hibernate.services;

import com.epam.balaian.hibernate.dao.StatusTypeDAO;
import com.epam.balaian.hibernate.model.StatusType;

/**
 * @author Vardan Balaian
 * @created 28.01.2020
 * @since 1.8
 */
public class StatusTypeService {

  private StatusTypeDAO statusTypeDAO;

  public StatusTypeService(StatusTypeDAO statusTypeDAO) {

    this.statusTypeDAO = statusTypeDAO;
  }

  public boolean checkStatusTypePresence(StatusType status) {

    StatusType existingStatus = statusTypeDAO.getStatusById(status.getStatusId());

    return existingStatus != null;
  }
}
