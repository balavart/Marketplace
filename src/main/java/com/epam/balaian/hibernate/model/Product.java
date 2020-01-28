package com.epam.balaian.hibernate.model;

import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * @author Vardan Balaian
 * @created 24.01.2020
 * @since 1.8
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "product", schema = "marketplace")
public class Product {

  private Long productId;
  private String productTitle;
  private String description;
  private User productOwner;
  private Bidding biddingByProduct;

  public Product(Long productId) {
    this.productId = productId;
  }

  public Product(String productTitle, String description, Long productId) {
    this.productTitle = productTitle;
    this.description = description;
    this.productId = productId;
  }

  public Product() {}

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "product_id", nullable = false)
  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  @Basic
  @Column(name = "product_title", nullable = false, length = 20)
  public String getProductTitle() {
    return productTitle;
  }

  public void setProductTitle(String productTitle) {
    this.productTitle = productTitle;
  }

  @Basic
  @Column(name = "description", nullable = false, length = 150)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @ManyToOne
  @JoinColumn(name = "product_owner_id_fk", referencedColumnName = "user_id", nullable = false)
  public User getProductOwner() {
    return productOwner;
  }

  public void setProductOwner(User userByProductOwnerIdFk) {
    this.productOwner = userByProductOwnerIdFk;
  }

  @OneToOne
  @JoinColumn(name = "bidding_id_fk", referencedColumnName = "bidding_id", nullable = false)
  public Bidding getBiddingByProduct() {
    return biddingByProduct;
  }

  public void setBiddingByProduct(Bidding biddingByProduct) {
    this.biddingByProduct = biddingByProduct;
  }

  @Override
  public String toString() {
    return "Product{"
        + "productId="
        + productId
        + ", productTitle='"
        + productTitle
        + '\''
        + ", description='"
        + description
        + '\''
        + ", userByProductOwnerIdFk="
        + productOwner
        + ", biddingByBiddingIdFk="
        + biddingByProduct
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Product product = (Product) o;
    return Objects.equals(productId, product.productId)
        && Objects.equals(productTitle, product.productTitle)
        && Objects.equals(description, product.description)
        && Objects.equals(productOwner, product.productOwner)
        && Objects.equals(biddingByProduct, product.biddingByProduct);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productId, productTitle, description, productOwner, biddingByProduct);
  }
}
