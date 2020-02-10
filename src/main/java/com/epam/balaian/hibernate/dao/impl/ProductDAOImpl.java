package com.epam.balaian.hibernate.dao.impl;

import com.epam.balaian.hibernate.dao.ProductDAO;
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
    SessionTerminal.openSessionAndTransaction();

    try {
      SessionTerminal.FACTORY.getCurrentSession().save(product);
      return product;
    } finally {
      SessionTerminal.closeSessionAndTransaction();
    }
  }

  @Override
  public Product getByProductId(long productId) {
    SessionTerminal.openSessionAndTransaction();

    try {
      return SessionTerminal.FACTORY.getCurrentSession().get(Product.class, productId);
    } finally {
      SessionTerminal.closeSessionAndTransaction();
    }
  }

  @Override
  public Product editProduct(String title, String description, long productId) {
    SessionTerminal.openSessionAndTransaction();

    try {
      Product productToUpdate =
          SessionTerminal.FACTORY.getCurrentSession().get(Product.class, productId);
      productToUpdate.setProductTitle(title);
      productToUpdate.setDescription(description);
      SessionTerminal.FACTORY.getCurrentSession().update(productToUpdate);
      return productToUpdate;
    } finally {
      SessionTerminal.closeSessionAndTransaction();
    }
  }

  @Override
  public Product deleteProduct(long productId) {
    SessionTerminal.openSessionAndTransaction();

    try {
      Product productToRemove =
          SessionTerminal.FACTORY.getCurrentSession().get(Product.class, productId);
      SessionTerminal.FACTORY.getCurrentSession().delete(productToRemove);
      return productToRemove;
    } finally {
      SessionTerminal.closeSessionAndTransaction();
    }
  }

  @Override
  public List<Product> getAllProducts() {
    SessionTerminal.openSessionAndTransaction();

    try {
      return SessionTerminal.FACTORY.getCurrentSession().createQuery("from Product").list();
    } finally {
      SessionTerminal.closeSessionAndTransaction();
    }
  }

  @Override
  public List<Product> getAllProductsByIdOwner(long idOwner) {
    SessionTerminal.openSessionAndTransaction();

    try {
      return SessionTerminal.FACTORY
          .getCurrentSession()
          .createQuery("from Product where productOwner.userId = " + idOwner)
          .list();
    } finally {
      SessionTerminal.closeSessionAndTransaction();
    }
  }
}
