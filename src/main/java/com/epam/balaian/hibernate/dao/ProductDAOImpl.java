package com.epam.balaian.hibernate.dao;

import com.epam.balaian.hibernate.model.Product;
import com.epam.balaian.hibernate.services.SessionTerminal;
import java.util.List;

/**
 * @author Vardan Balaian
 * @created 1/24/2020
 * @since 1.8
 */
public class ProductDAOImpl implements ProductDAO {

  @Override
  public Product addProduct(Product product) {
    SessionTerminal.factory.getCurrentSession().save(product);
    return product;
  }

  @Override
  public Product getByProductId(long productId) {
    return SessionTerminal.factory.getCurrentSession().get(Product.class, productId);
  }

  @Override
  public Product editProduct(String title, String description, long productId) {
    Product productToUpdate =
        SessionTerminal.factory.getCurrentSession().get(Product.class, productId);
    productToUpdate.setProductTitle(title);
    productToUpdate.setDescription(description);
    SessionTerminal.factory.getCurrentSession().update(productToUpdate);
    return productToUpdate;
  }

  @Override
  public Product deleteProduct(long productId) {
    Product productToRemove =
        SessionTerminal.factory.getCurrentSession().get(Product.class, productId);
    SessionTerminal.factory.getCurrentSession().delete(productToRemove);
    return productToRemove;
  }

  @Override
  public List<Product> getAllProducts() {
    return SessionTerminal.factory.getCurrentSession().createQuery("from Product").list();
  }

  @Override
  public List<Product> getAllProductsByIdOwner(long idOwner) {
    return SessionTerminal.factory
        .getCurrentSession()
        .createQuery("from Product where productId = " + idOwner)
        .list();
  }
}
