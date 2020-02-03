package com.epam.balaian.hibernate.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.epam.balaian.hibernate.dao.ProductDAO;
import com.epam.balaian.hibernate.model.Product;
import com.epam.balaian.hibernate.model.User;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ProductServiceTest {

  @Mock private ProductDAO productDAO;

  @InjectMocks
  private ProductService productService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void checkProductAdditionTrue() {
    Product addedProduct = new Product("Something", "Something", 1L);

    given(productDAO.addProduct(any(Product.class))).willReturn(addedProduct);

    final boolean actualResult = productService.checkProductAddition(addedProduct);

    assertThat(actualResult).isTrue();
    verify(productDAO, times(1)).addProduct(addedProduct);
    verify(productDAO, never()).deleteProduct(anyLong());
  }

  @Test
  void checkProductAdditionFalse() {
    given(productDAO.addProduct(any(Product.class))).willReturn(null);

    final boolean actualResult = productService.checkProductAddition(new Product(1L));

    assertThat(actualResult).isFalse();
    verify(productDAO, times(1)).addProduct(new Product(1L));
    verify(productDAO, never()).deleteProduct(anyLong());
  }

  @Test
  void checkProductAdditionException() {
    assertThrows(
        Exception.class,
        () -> {
          given(productDAO.addProduct(any(Product.class))).willThrow(Exception.class);

          productService.checkProductAddition(any(Product.class));
        });
  }

  @Test
  void checkProductPresenceTrue() {
    Product productReceived = new Product("Something", "Something", 1L);

    given(productDAO.getByProductId(anyLong())).willReturn(productReceived);

    final boolean actualResult = productService.checkProductPresence(productReceived);

    assertThat(actualResult).isTrue();
    verify(productDAO, times(1)).getByProductId(1L);
    verify(productDAO, never()).deleteProduct(anyLong());
  }

  @Test
  public void checkProductPresenceFalse() {
    given(productDAO.getByProductId(anyLong())).willReturn(null);

    final boolean actualResult = productService.checkProductPresence(new Product(1L));

    assertThat(actualResult).isFalse();
    verify(productDAO, times(1)).getByProductId(1L);
    verify(productDAO, never()).deleteProduct(anyLong());
  }

  @Test
  public void checkProductPresenceException() {
    assertThrows(
        Exception.class,
        () -> {
          given(productDAO.getByProductId(any(Long.class))).willThrow(Exception.class);

          productService.checkProductPresence(any(Product.class));
        });
  }

  @Test
  void checkProductEditingTrue() {
    Product editedProduct = new Product("Something", "Something", 1L);

    given(productDAO.editProduct(isA(String.class), isA(String.class), isA(Long.TYPE)))
        .willReturn(editedProduct);

    final boolean actualResult = productService.checkProductEditing(editedProduct);

    assertThat(actualResult).isTrue();
    verify(productDAO, times(1)).editProduct(isA(String.class), isA(String.class), isA(Long.TYPE));
    verify(productDAO, never()).deleteProduct(anyLong());
  }

  @Test
  void checkProductEditingFalse() {
    given(productDAO.editProduct(isA(String.class), isA(String.class), isA(Long.TYPE)))
        .willReturn(null);

    final boolean actualResult =
        productService.checkProductEditing(new Product("Something", "Something", 1L));

    assertThat(actualResult).isFalse();

    verify(productDAO, times(1)).editProduct(isA(String.class), isA(String.class), isA(Long.TYPE));
    verify(productDAO, never()).deleteProduct(anyLong());
  }

  @Test
  void checkProductEditingException() {
    assertThrows(
        Exception.class,
        () -> {
          given(productDAO.editProduct(isA(String.class), isA(String.class), isA(Long.TYPE)))
              .willThrow(Exception.class);

          productService.checkProductEditing(any(Product.class));
        });
  }

  @Test
  void checkProductRemovalTrue() {
    Product deletedProduct = new Product("Something", "Something", 1L);

    given(productDAO.deleteProduct(anyLong())).willReturn(deletedProduct);

    final boolean actualResult = productService.checkProductRemoval(deletedProduct);

    assertThat(actualResult).isTrue();
    verify(productDAO, times(1)).deleteProduct(anyLong());
    verify(productDAO, atLeastOnce()).deleteProduct(anyLong());
  }

  @Test
  void checkProductRemovalFalse() {
    given(productDAO.deleteProduct(any(Long.class))).willReturn(null);

    final boolean actualResult =
        productService.checkProductRemoval(new Product("Something", "Something", 1L));

    assertThat(actualResult).isFalse();
    verify(productDAO, times(1)).deleteProduct(anyLong());
    verify(productDAO, atLeastOnce()).deleteProduct(anyLong());
  }

  @Test
  void checkProductRemovalException() {
    assertThrows(
        Exception.class,
        () -> {
          given(productDAO.deleteProduct(anyLong())).willThrow(Exception.class);

          productService.checkProductRemoval(any(Product.class));
        });
  }

  @Test
  void checkAllProductsPresenceTrue() {
    List<Product> allExistingProducts =
        Arrays.asList(new Product(1L), new Product(2L), new Product(3L));

    given(productDAO.getAllProducts()).willReturn(allExistingProducts);

    final boolean actualResult = productService.checkAllProductsPresence(allExistingProducts);

    assertThat(actualResult).isTrue();
    verify(productDAO, times(1)).getAllProducts();
    verify(productDAO, never()).deleteProduct(anyLong());
  }

  @Test
  void checkAllProductsPresenceFalse() {
    given(productDAO.getAllProducts()).willReturn(null);

    final boolean actualResult =
        productService.checkAllProductsPresence(
            Arrays.asList(new Product(1L), new Product(2L), new Product(3L)));

    assertThat(actualResult).isFalse();
    verify(productDAO, times(1)).getAllProducts();
    verify(productDAO, never()).deleteProduct(anyLong());
  }

  @Test
  void checkAllProductsPresenceException() {
    assertThrows(
        Exception.class,
        () -> {
          given(productDAO.getAllProducts()).willThrow(Exception.class);

          productService.checkAllProductsPresence(any());
        });
  }

  @Test
  void checkAllProductsPresenceByIdOwnerTrue() {
    List<Product> allExistingProductsByOwner =
        Arrays.asList(new Product(1L), new Product(2L), new Product(3L));
    User productOwner = new User(1L);

    given(productDAO.getAllProductsByIdOwner(anyLong()))
        .willReturn(allExistingProductsByOwner);

    final boolean actualResult =
        productService.checkAllProductsPresenceByIdOwner(allExistingProductsByOwner, productOwner);

    assertThat(actualResult).isTrue();
    verify(productDAO, times(1)).getAllProductsByIdOwner(anyLong());
    verify(productDAO, never()).deleteProduct(anyLong());
  }

  @Test
  void checkAllProductsPresenceByIdOwnerFalse() {
    List<Product> allExistingProductsByOwner =
        Arrays.asList(new Product(1L), new Product(2L), new Product(3L));
    User productOwner = new User(1L);

    given(productDAO.getAllProductsByIdOwner(any(Long.class))).willReturn(null);

    final boolean actualResult =
        productService.checkAllProductsPresenceByIdOwner(allExistingProductsByOwner, productOwner);

    assertThat(actualResult).isFalse();
    verify(productDAO, times(1)).getAllProductsByIdOwner(anyLong());
    verify(productDAO, never()).deleteProduct(anyLong());
  }

  @Test
  void checkAllProductsPresenceByIdOwnerException() {
    assertThrows(
        Exception.class,
        () -> {
          given(productDAO.getAllProductsByIdOwner(anyLong())).willThrow(Exception.class);

          productService.checkAllProductsPresenceByIdOwner(any(), any(User.class));
        });
  }
}
