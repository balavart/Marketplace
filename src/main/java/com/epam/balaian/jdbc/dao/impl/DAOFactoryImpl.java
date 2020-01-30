package com.epam.balaian.jdbc.dao.impl;

import com.epam.balaian.jdbc.dao.BiddingDAO;
import com.epam.balaian.jdbc.dao.DAOFactory;
import com.epam.balaian.jdbc.dao.ProductDAO;
import com.epam.balaian.jdbc.dao.UserDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Vardan_Balaian
 * @created 1/22/2020
 * @since 1.8
 */
public class DAOFactoryImpl implements DAOFactory {

  private static final String URL =
      "jdbc:mysql://localhost:3306/marketplace?useUnicode=true&serverTimezone=UTC";
  private static final String USERNAME = "root";
  private static final String PASSWORD = "root";

  private static DAOFactory factory;

  public DAOFactoryImpl() {
    registerDriver();
  }

  private void registerDriver() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      System.out.println("Driver established");
    } catch (ClassNotFoundException e) {
      System.err.println("Failed to load driver class");
    }
  }

  public static synchronized DAOFactory getInstance() {
    if (factory == null) {
      factory = new DAOFactoryImpl();
    }
    return factory;
  }

  public static Connection getConnection() {
    Connection connection = null;
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    } catch (SQLException e) {
      System.err.println("Could not connect to the database");
      e.printStackTrace();
    }
    return connection;
  }

  @Override
  public UserDAO getUserDAO() {
    return new UserDAOImpl();
  }

  @Override
  public ProductDAO getProductDAO() {
    return new ProductDAOImpl();
  }

  @Override
  public BiddingDAO getBiddingDAO() {
    return new BiddingDAOImpl();
  }
}
