package com.epam.balaian.hibernate.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * @author balavart
 * @created 24.01.2020
 * @since 1.8
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "product", schema = "marketplace", catalog = "")
public class ProductEntity {

  private Long productId;
  private String productTitle;
  private String description;
  private UserEntity userByProductOwnerIdFk;
  private BiddingEntity biddingByBiddingIdFk;

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
  public UserEntity getUserByProductOwnerIdFk() {
    return userByProductOwnerIdFk;
  }

  public void setUserByProductOwnerIdFk(UserEntity userByProductOwnerIdFk) {
    this.userByProductOwnerIdFk = userByProductOwnerIdFk;
  }

  @ManyToOne
  @JoinColumn(name = "bidding_id_fk", referencedColumnName = "bidding_id", nullable = false)
  public BiddingEntity getBiddingByBiddingIdFk() {
    return biddingByBiddingIdFk;
  }

  public void setBiddingByBiddingIdFk(BiddingEntity biddingByBiddingIdFk) {
    this.biddingByBiddingIdFk = biddingByBiddingIdFk;
  }

  @Override
  public String toString() {
    return "ProductEntity{"
        + "productId="
        + productId
        + ", productTitle='"
        + productTitle
        + '\''
        + ", description='"
        + description
        + '\''
        + ", userByProductOwnerIdFk="
        + userByProductOwnerIdFk
        + ", biddingByBiddingIdFk="
        + biddingByBiddingIdFk
        + '}';
  }
}
