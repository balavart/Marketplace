package com.epam.balaian.hibernate.dao;

import com.epam.balaian.hibernate.model.ProductEntity;
import java.util.Collection;

/**
 * @author Vardan_Balaian
 * @created 1/24/2020
 * @since 1.8
 */
public interface ProductEntityDAO {

  void addProduct(ProductEntity product);

  ProductEntity getByIdProduct(long productId);

  void editProduct(String title, String description, long productId);

  void deleteProduct(long productId);

  Collection<ProductEntity> getAllProducts();

  Collection<ProductEntity> getAllProductsByIdOwner(final long ownerId);
}
