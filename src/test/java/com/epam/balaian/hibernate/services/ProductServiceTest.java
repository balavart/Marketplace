package com.epam.balaian.hibernate.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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
  void checkProductAdditionTrue() {
    Product addedProduct = new Product("Something", "Something", 1L);

    given(productDAO.addProduct(any(Product.class))).willReturn(addedProduct);
    boolean productsExists = productService.checkProductAddition(addedProduct);

    assertThat(productsExists).isTrue();

    verify(productDAO, times(1)).addProduct(addedProduct);
    verify(productDAO, never()).deleteProduct(any(Long.TYPE));
  }

  @Test
  void checkProductAdditionFalse() {
    given(productDAO.addProduct(any(Product.class))).willReturn(null);
    boolean productsExists = productService.checkProductAddition(new Product(1L));

    assertThat(productsExists).isFalse();

    verify(productDAO, times(1)).addProduct(new Product(1L));
    verify(productDAO, never()).deleteProduct(any(Long.TYPE));
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

    given(productDAO.getByProductId(any(Long.TYPE))).willReturn(productReceived);
    boolean productExists = productService.checkProductPresence(productReceived);

    assertThat(productExists).isTrue();

    verify(productDAO, times(1)).getByProductId(1L);
    verify(productDAO, never()).deleteProduct(any(Long.TYPE));
  }

  @Test
  public void checkProductPresenceFalse() {
    given(productDAO.getByProductId(any(Long.TYPE))).willReturn(null);
    boolean productExists = productService.checkProductPresence(new Product(1L));

    assertThat(productExists).isFalse();

    verify(productDAO, times(1)).getByProductId(1L);
    verify(productDAO, never()).deleteProduct(any(Long.TYPE));
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
    boolean productExists = productService.checkProductEditing(editedProduct);

    assertThat(productExists).isTrue();

    verify(productDAO, times(1)).editProduct(isA(String.class), isA(String.class), isA(Long.TYPE));
    verify(productDAO, never()).deleteProduct(any(Long.TYPE));
  }

  @Test
  void checkProductEditingFalse() {
    given(productDAO.editProduct(isA(String.class), isA(String.class), isA(Long.TYPE)))
        .willReturn(null);
    boolean productExists =
        productService.checkProductEditing(new Product("Something", "Something", 1L));

    assertThat(productExists).isFalse();

    verify(productDAO, times(1)).editProduct(isA(String.class), isA(String.class), isA(Long.TYPE));
    verify(productDAO, never()).deleteProduct(any(Long.TYPE));
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

    given(productDAO.deleteProduct(any(Long.TYPE))).willReturn(deletedProduct);
    boolean productExists = productService.checkProductRemoval(deletedProduct);

    assertThat(productExists).isTrue();

    verify(productDAO, times(1)).deleteProduct(any(Long.TYPE));
    verify(productDAO, atLeastOnce()).deleteProduct(any(Long.TYPE));
  }

  @Test
  void checkProductRemovalFalse() {
    given(productDAO.deleteProduct(any(Long.class))).willReturn(null);
    boolean productExists =
        productService.checkProductRemoval(new Product("Something", "Something", 1L));

    assertThat(productExists).isFalse();

    verify(productDAO, times(1)).deleteProduct(any(Long.TYPE));
    verify(productDAO, atLeastOnce()).deleteProduct(any(Long.TYPE));
  }

  @Test
  void checkProductRemovalException() {
    assertThrows(
        Exception.class,
        () -> {
          given(productDAO.deleteProduct(any(Long.TYPE))).willThrow(Exception.class);
          productService.checkProductRemoval(any(Product.class));
        });
  }

  @Test
  void checkAllProductsPresenceTrue() {
    List<Product> allExistingProducts =
        Arrays.asList(new Product(1L), new Product(2L), new Product(3L));

    given(productDAO.getAllProducts()).willReturn(allExistingProducts);
    boolean productsExists = productService.checkAllProductsPresence(allExistingProducts);

    assertThat(productsExists).isTrue();

    verify(productDAO, times(1)).getAllProducts();
    verify(productDAO, never()).deleteProduct(any(Long.TYPE));
  }

  @Test
  void checkAllProductsPresenceFalse() {
    given(productDAO.getAllProducts()).willReturn(null);
    boolean productsExists =
        productService.checkAllProductsPresence(
            Arrays.asList(new Product(1L), new Product(2L), new Product(3L)));

    assertThat(productsExists).isFalse();

    verify(productDAO, times(1)).getAllProducts();
    verify(productDAO, never()).deleteProduct(any(Long.TYPE));
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

    given(productDAO.getAllProductsByIdOwner(any(Long.TYPE)))
        .willReturn(allExistingProductsByOwner);
    boolean productsExists =
        productService.checkAllProductsPresenceByIdOwner(allExistingProductsByOwner, productOwner);

    assertThat(productsExists).isTrue();

    verify(productDAO, times(1)).getAllProductsByIdOwner(any(Long.TYPE));
    verify(productDAO, never()).deleteProduct(any(Long.TYPE));
  }

  @Test
  void checkAllProductsPresenceByIdOwnerFalse() {
    List<Product> allExistingProductsByOwner =
        Arrays.asList(new Product(1L), new Product(2L), new Product(3L));
    User productOwner = new User(1L);

    given(productDAO.getAllProductsByIdOwner(any(Long.class))).willReturn(null);
    boolean productsExists =
        productService.checkAllProductsPresenceByIdOwner(allExistingProductsByOwner, productOwner);

    assertThat(productsExists).isFalse();

    verify(productDAO, times(1)).getAllProductsByIdOwner(any(Long.TYPE));
    verify(productDAO, never()).deleteProduct(any(Long.TYPE));
  }

  @Test
  void checkAllProductsPresenceByIdOwnerException() {
    assertThrows(
        Exception.class,
        () -> {
          given(productDAO.getAllProductsByIdOwner(any(Long.TYPE))).willThrow(Exception.class);
          productService.checkAllProductsPresenceByIdOwner(any(), any(User.class));
        });
  }
}
