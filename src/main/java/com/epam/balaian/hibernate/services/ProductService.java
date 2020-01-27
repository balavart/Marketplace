package com.epam.balaian.hibernate.services;

import com.epam.balaian.hibernate.dao.ProductDAO;
import com.epam.balaian.hibernate.model.Product;

/**
 * @author Vardan Balaian
 * @created 1/27/2020
 * @since 1.8
 */
public class ProductService {

  private ProductDAO productDAO;

  public ProductService(ProductDAO productDAO) {
    this.productDAO = productDAO;
  }

  public boolean checkProductPresence(Product product) {

    Product productExample = productDAO.getByProductId(product.getProductId());

    return productExample != null;
  }
}
