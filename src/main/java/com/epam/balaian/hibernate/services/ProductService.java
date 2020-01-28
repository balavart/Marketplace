package com.epam.balaian.hibernate.services;

import com.epam.balaian.hibernate.dao.ProductDAO;
import com.epam.balaian.hibernate.model.Product;
import com.epam.balaian.hibernate.model.User;
import java.util.List;

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

  public boolean checkProductAddition(Product product) {
    Product addedProduct = productDAO.addProduct(product);

    return addedProduct != null;
  }

  public boolean checkProductPresence(Product product) {
    Product existingProduct = productDAO.getByProductId(product.getProductId());

    return existingProduct != null;
  }

  public boolean checkProductEditing(Product product) {
    Product editedProduct =
        productDAO.editProduct(
            product.getProductTitle(), product.getDescription(), product.getProductId());

    return editedProduct != null;
  }

  public boolean checkProductRemoval(Product product) {
    Product deletedProduct = productDAO.deleteProduct(product.getProductId());

    return deletedProduct != null;
  }

  public boolean checkAllProductsPresence(List<Product> allExistingProducts) {
    allExistingProducts = productDAO.getAllProducts();

    return allExistingProducts != null;
  }

  public boolean checkAllProductsPresenceByIdOwner(
      List<Product> allExistingProducts, User productOwner) {
    allExistingProducts = productDAO.getAllProductsByIdOwner(productOwner.getUserId());

    return allExistingProducts != null;
  }
}
