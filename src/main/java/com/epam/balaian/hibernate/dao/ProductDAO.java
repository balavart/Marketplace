package com.epam.balaian.hibernate.dao;

import com.epam.balaian.hibernate.model.Product;
import java.util.List;

/**
 * @author Vardan Balaian
 * @created 1/24/2020
 * @since 1.8
 */
public interface ProductDAO {

  Product addProduct(Product product);

  Product getByProductId(long productId);

  Product editProduct(String title, String description, long productId);

  Product deleteProduct(long productId);

  List<Product> getAllProducts();

  List<Product> getAllProductsByIdOwner(long ownerId);
}
