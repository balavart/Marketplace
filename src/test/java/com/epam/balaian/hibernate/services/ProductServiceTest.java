package com.epam.balaian.hibernate.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.epam.balaian.hibernate.dao.ProductDAO;
import com.epam.balaian.hibernate.model.Product;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ProductServiceTest {

  @Mock private ProductDAO productDAO;

  private ProductService productService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    this.productService = new ProductService(productDAO);
  }

  @Test
  void checkProductPresenceTrue() {
    given(productDAO.getByProductId(1L)).willReturn(new Product(1L));
    boolean productExists = productService.checkProductPresence(new Product(1L));

    assertThat(productExists).isTrue();

    verify(productDAO, times(1)).getByProductId(1L);
    verify(productDAO, never()).deleteProduct(any(Long.class));
  }

  @Test
  public void checkProductPresenceFalse() {
    given(productDAO.getByProductId(1L)).willReturn(null);
    boolean productExists = productService.checkProductPresence(new Product(1L));

    assertThat(productExists).isFalse();

    verify(productDAO, times(1)).getByProductId(1L);
    verify(productDAO, never()).deleteProduct(any(Long.class));
  }

  @Test
  public void checkProductPresenceException() {
    assertThrows(
        Exception.class,
        () -> {
          given(productDAO.getByProductId(any(Long.class))).willThrow(Exception.class);
          productService.checkProductPresence(new Product(1L));
        });
  }

  @Test
  void addProductTest() {
    doNothing().when(productDAO).addProduct(any(Product.class));

    productDAO.addProduct(new Product(1L));

    verify(productDAO, times(1)).addProduct(new Product(1L));
    verify(productDAO, never()).deleteProduct(any(Long.class));
  }

  @Test
  public void addProductException() {
    assertThrows(
        Exception.class,
        () -> {
          doThrow().when(productDAO).addProduct(any(Product.class));
        });
  }

  @Test
  void editProductTest() {
    doNothing().when(productDAO).editProduct(isA(String.class), isA(String.class), isA(Long.class));

    productDAO.editProduct("Some", "Something", 1L);

    verify(productDAO, times(1)).editProduct(any(String.class), any(String.class), any(Long.class));
    verify(productDAO, never()).deleteProduct(any(Long.class));
  }

  @Test
  void editProductException() {
    assertThrows(
        Exception.class,
        () -> {
          doThrow()
              .when(productDAO)
              .editProduct(isA(String.class), isA(String.class), isA(Long.class));
        });
  }

  @Test
  void deleteProductTest() {
    doNothing().when(productDAO).deleteProduct(isA(Long.class));
    productDAO.deleteProduct(1L);

    verify(productDAO, times(1)).deleteProduct(1L);
    verify(productDAO, atLeastOnce()).deleteProduct(any(Long.class));
  }

  @Test
  void deleteProductException() {
    assertThrows(
        Exception.class,
        () -> {
          doThrow().when(productDAO).deleteProduct(any(Long.class));
        });
  }

  @Test
  void getAllProductsTest() {
    List<Product> products = Arrays.asList(new Product(1L), new Product(2L));

    when(productDAO.getAllProducts()).thenReturn(products);

    productDAO.getAllProducts();

    verify(productDAO, times(1)).getAllProducts();
  }

  @Test
  void getAllProductsException() {
    assertThrows(
        Exception.class,
        () -> {
          doThrow().when(productDAO).getAllProducts();
        });
  }

  @Test
  void getAllProductsByIdOwnerTest() {
    List<Product> productsByUser = Arrays.asList(new Product(1L), new Product(2L));

    when(productDAO.getAllProductsByIdOwner(any(Long.class))).thenReturn(productsByUser);

    productDAO.getAllProductsByIdOwner(any(Long.class));

    verify(productDAO, times(1)).getAllProductsByIdOwner(any(Long.class));
  }

  @Test
  void getAllProductsByIdOwnerException() {
    assertThrows(
        Exception.class,
        () -> {
          doThrow().when(productDAO).getAllProductsByIdOwner(any(Long.class));
        });
  }
}
