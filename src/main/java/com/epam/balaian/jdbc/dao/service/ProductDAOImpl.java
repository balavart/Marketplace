package com.epam.balaian.jdbc.dao.service;

import static com.epam.balaian.jdbc.dao.BDScheme.DESCRIPTION;
import static com.epam.balaian.jdbc.dao.BDScheme.ID_BIDDING_FK;
import static com.epam.balaian.jdbc.dao.BDScheme.ID_PRODUCT;
import static com.epam.balaian.jdbc.dao.BDScheme.ID_PRODUCT_OWNER_FK;
import static com.epam.balaian.jdbc.dao.BDScheme.PRODUCT_TABLE;
import static com.epam.balaian.jdbc.dao.BDScheme.PRODUCT_TITLE;

import com.epam.balaian.jdbc.dao.ProductDAO;
import com.epam.balaian.jdbc.model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Vardan_Balaian
 * @created 1/22/2020
 * @since 1.8
 */
public class ProductDAOImpl implements ProductDAO {

  @Override
  public void addProduct(Product product) throws SQLException {

    String sqlProductInsert =
        String.format(
            "INSERT INTO %s (%s, %s, %s, %s) VALUES (?, ?, ?, ?)",
            PRODUCT_TABLE, PRODUCT_TITLE, DESCRIPTION, ID_PRODUCT_OWNER_FK, ID_BIDDING_FK);

    try (Connection connection = DAOFactoryImpl.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlProductInsert)) {
      System.out.println("Connection established");

      preparedStatement.setString(1, product.getTitle());
      preparedStatement.setString(2, product.getDescription());
      preparedStatement.setLong(3, product.getIdProductOwner());
      preparedStatement.setLong(4, product.getIdBidding());

      preparedStatement.execute();
    }
  }

  @Override
  public Product getByIdProduct(long idProduct) throws SQLException {

    String sqlProductSelectOne =
        String.format(
            "SELECT %s, %s, %s, %s FROM %s WHERE %s = ?",
            PRODUCT_TITLE,
            DESCRIPTION,
            ID_PRODUCT_OWNER_FK,
            ID_BIDDING_FK,
            PRODUCT_TABLE,
            ID_PRODUCT);

    try (Connection connection = DAOFactoryImpl.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlProductSelectOne)) {
      preparedStatement.setLong(1, idProduct);
      ResultSet rs = preparedStatement.executeQuery();
      Product productInfo = null;
      while (rs.next()) {
        String productTitle = rs.getString(1);
        String description = rs.getString(2);
        long idProductOwner = rs.getLong(3);
        long idBidding = rs.getLong(4);
        productInfo = new Product(idProduct, productTitle, description, idProductOwner, idBidding);
      }
      return productInfo;
    }
  }

  @Override
  public void updateProduct(String title, String description, long idProduct) throws SQLException {

    String sqlProductUpdate =
        String.format(
            "UPDATE %s SET %s = ?, %s = ? WHERE %s = ?",
            PRODUCT_TABLE, PRODUCT_TITLE, DESCRIPTION, ID_PRODUCT);

    try (Connection connection = DAOFactoryImpl.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlProductUpdate)) {
      System.out.println("Connection established");

      preparedStatement.setString(1, title);
      preparedStatement.setString(2, description);
      preparedStatement.setLong(3, idProduct);

      int updatedValues = preparedStatement.executeUpdate();
      System.out.println("Values updated: " + updatedValues);
    }
  }

  @Override
  public void deleteProduct(long idProduct) throws SQLException {

    String sqlProductDelete =
        String.format("DELETE FROM %s WHERE %s = ?", PRODUCT_TABLE, ID_PRODUCT);

    try (Connection connection = DAOFactoryImpl.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlProductDelete)) {
      System.out.println("Connection established");

      preparedStatement.setLong(1, idProduct);
      preparedStatement.execute();
    }
  }

  @Override
  public List<Product> getAllProducts() throws SQLException {

    List<Product> productList = new ArrayList<>();

    String sqlProductSelectAll =
        String.format(
            "SELECT %s, %s, %s, %s, %s FROM %s ",
            ID_PRODUCT,
            PRODUCT_TITLE,
            DESCRIPTION,
            ID_PRODUCT_OWNER_FK,
            ID_BIDDING_FK,
            PRODUCT_TABLE);

    try (Connection connection = DAOFactoryImpl.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlProductSelectAll)) {
      System.out.println("Connection established");

      ResultSet rs = preparedStatement.executeQuery();
      while (rs.next()) {
        long idProduct = rs.getLong(1);
        String title = rs.getString(2);
        String description = rs.getString(3);
        long idProductOwner_fk = rs.getLong(4);
        long idBidding_fk = rs.getLong(5);
        productList.add(
            new Product(idProduct, title, description, idProductOwner_fk, idBidding_fk));
      }
      return productList;
    }
  }

  @Override
  public List<Product> getAllProductsByIdOwner(final long idOwner) throws SQLException {
    return getAllProducts().stream()
        .filter(product -> product.getIdProductOwner() == idOwner)
        .collect(Collectors.toList());
  }
}
