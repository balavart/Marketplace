package com.epam.balaian.jdbc.dao;

/**
 * @author Vardan_Balaian
 * @created 1/22/2020
 * @since 1.8
 */
public interface DAOFactory {

  UserDAO getUserDAO();

  ProductDAO getProductDAO();

  BiddingDAO getBiddingDAO();
}
