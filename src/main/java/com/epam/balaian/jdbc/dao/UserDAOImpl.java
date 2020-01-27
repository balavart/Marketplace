package com.epam.balaian.jdbc.dao;

import static com.epam.balaian.jdbc.dao.BDScheme.CITY;
import static com.epam.balaian.jdbc.dao.BDScheme.EMAIL;
import static com.epam.balaian.jdbc.dao.BDScheme.FULL_NAME;
import static com.epam.balaian.jdbc.dao.BDScheme.ID_ROLE_FK;
import static com.epam.balaian.jdbc.dao.BDScheme.LOGIN_NAME;
import static com.epam.balaian.jdbc.dao.BDScheme.PASSWORD;
import static com.epam.balaian.jdbc.dao.BDScheme.PHONE_NUMBER;
import static com.epam.balaian.jdbc.dao.BDScheme.USER_TABLE;

import com.epam.balaian.jdbc.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Vardan_Balaian
 * @created 1/22/2020
 * @since 1.8
 */
public class UserDAOImpl implements UserDAO {

  @Override
  public void addUser(User user) {

    String sqlUserInsert =
        String.format(
            "INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?, ?)",
            USER_TABLE, LOGIN_NAME, PASSWORD, FULL_NAME, CITY, EMAIL, PHONE_NUMBER, ID_ROLE_FK);

    try (Connection connection = DAOFactoryImpl.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlUserInsert)) {
      System.out.println("Connection established");

      preparedStatement.setString(1, user.getLoginName());
      preparedStatement.setString(2, user.getPassword());
      preparedStatement.setString(3, user.getFullName());
      preparedStatement.setString(4, user.getCity());
      preparedStatement.setString(5, user.getEmail());
      preparedStatement.setString(6, user.getPhoneNumber());
      preparedStatement.setInt(7, user.getIdRole());

      preparedStatement.execute();

    } catch (SQLException e) {
      System.err.println("Could not connect to the database");
      e.printStackTrace();
    }
  }
}
