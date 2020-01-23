package com.epam.balaian.jdbc.dao;

import com.epam.balaian.jdbc.model.Product;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Vardan_Balaian
 * @created 1/22/2020
 * @since 1.8
 */
public interface ProductDAO {

  void addProduct(Product product) throws SQLException;

  Product getByIdProduct(long idProduct) throws SQLException;

  void updateProduct(String title, String description, long idProduct) throws SQLException;

  void deleteProduct(long idProduct) throws SQLException;

  List<Product> getAllProducts() throws SQLException;

  List<Product> getAllProductsByIdOwner(final long idOwner) throws SQLException;
}
