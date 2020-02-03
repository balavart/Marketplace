package com.epam.balaian.hibernate.dao;

import com.epam.balaian.hibernate.model.StatusType;

/**
 * @author Vardan Balaian
 * @created 1/24/2020
 * @since 1.8
 */
public interface StatusTypeDAO {

  StatusType getStatusById(int statusId);
}
