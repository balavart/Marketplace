package com.epam.balaian.hibernate.dao;

import com.epam.balaian.hibernate.model.Product;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * @author Vardan Balaian
 * @created 1/24/2020
 * @since 1.8
 */
public class ProductDAOImpl implements ProductDAO {
  private final SessionFactory factory;

  public ProductDAOImpl() {
    this.factory = new Configuration().configure().buildSessionFactory();
  }

  @Override
  public void addProduct(Product product) {
    final Session session = factory.openSession();
    Transaction tx = session.beginTransaction();

    try {
      session.save(product);
    } finally {
      tx.commit();
      session.close();
    }
  }

  @Override
  public Product getByProductId(long productId) {
    final Session session = factory.openSession();
    Transaction tx = session.beginTransaction();
    try {
      return session.get(Product.class, productId);
    } finally {
      tx.commit();
      session.close();
    }
  }

  @Override
  public void editProduct(String title, String description, long productId) {
    final Session session = factory.openSession();
    Transaction tx = session.beginTransaction();
    try {
      Product productToUpdate = session.get(Product.class, productId);
      productToUpdate.setProductTitle(title);
      productToUpdate.setDescription(description);
      session.update(productToUpdate);
    } finally {
      tx.commit();
      session.close();
    }
  }

  @Override
  public void deleteProduct(long productId) {
    final Session session = factory.openSession();
    Transaction tx = session.beginTransaction();
    try {
      Product productToRemove = session.get(Product.class, productId);
      session.delete(productToRemove);
    } finally {
      tx.commit();
      session.close();
    }
  }

  @Override
  public List<Product> getAllProducts() {
    final Session session = factory.openSession();
    Transaction tx = session.beginTransaction();
    try {
      return session.createQuery("from Product").list();
    } finally {
      tx.commit();
      session.close();
    }
  }

  @Override
  public List<Product> getAllProductsByIdOwner(long idOwner) {
    final Session session = factory.openSession();
    Transaction tx = session.beginTransaction();
    try {
      return session.createQuery("from Product where productId = " + idOwner).list();
    } finally {
      tx.commit();
      session.close();
    }
  }
}
