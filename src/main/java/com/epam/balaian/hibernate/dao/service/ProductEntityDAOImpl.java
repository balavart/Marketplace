package com.epam.balaian.hibernate.dao.service;

import com.epam.balaian.hibernate.dao.ProductEntityDAO;
import com.epam.balaian.hibernate.model.ProductEntity;
import java.util.Collection;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * @author Vardan Balaian
 * @created 1/24/2020
 * @since 1.8
 */
public class ProductEntityDAOImpl implements ProductEntityDAO {
  private final SessionFactory factory;

  public ProductEntityDAOImpl() {
    this.factory = new Configuration().configure().buildSessionFactory();
  }

  @Override
  public void addProduct(ProductEntity product) {
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
  public ProductEntity getByIdProduct(long productId) {
    final Session session = factory.openSession();
    Transaction tx = session.beginTransaction();
    try {
      return session.get(ProductEntity.class, productId);
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
      ProductEntity productToUpdate = session.get(ProductEntity.class, productId);
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
      ProductEntity productToRemove = session.get(ProductEntity.class, productId);
      session.delete(productToRemove);
    } finally {
      tx.commit();
      session.close();
    }
  }

  @Override
  public Collection<ProductEntity> getAllProducts() {
    final Session session = factory.openSession();
    Transaction tx = session.beginTransaction();
    try {
      return session.createQuery("from ProductEntity").list();
    } finally {
      tx.commit();
      session.close();
    }
  }

  @Override
  public Collection<ProductEntity> getAllProductsByIdOwner(long idOwner) {
    final Session session = factory.openSession();
    Transaction tx = session.beginTransaction();
    try {
      return session.createQuery("from ProductEntity where productId = " + idOwner).list();
    } finally {
      tx.commit();
      session.close();
    }
  }
}
