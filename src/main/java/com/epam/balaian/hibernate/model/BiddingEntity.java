package com.epam.balaian.hibernate.model;

import java.sql.Date;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "bidding", schema = "marketplace", catalog = "")
public class BiddingEntity {

  private Long biddingId;
  private Double startingPrice;
  private Date offerEndDate;
  private Double bestOffer;
  private UserEntity userBySupposedBidderIdFk;
  private StatusTypeEntity statusTypeByStatusIdFk;
  private Collection<ProductEntity> productsByBiddingId;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "bidding_id", nullable = false)
  public Long getBiddingId() {
    return biddingId;
  }

  public void setBiddingId(Long biddingId) {
    this.biddingId = biddingId;
  }

  @Basic
  @Column(name = "starting_price", nullable = false, precision = 0)
  public Double getStartingPrice() {
    return startingPrice;
  }

  public void setStartingPrice(Double startingPrice) {
    this.startingPrice = startingPrice;
  }

  @Basic
  @Column(name = "offer_end_date", nullable = false)
  public Date getOfferEndDate() {
    return offerEndDate;
  }

  public void setOfferEndDate(Date offerEndDate) {
    this.offerEndDate = offerEndDate;
  }

  @Basic
  @Column(name = "best_offer", nullable = true, precision = 0)
  public Double getBestOffer() {
    return bestOffer;
  }

  public void setBestOffer(Double bestOffer) {
    this.bestOffer = bestOffer;
  }

  @ManyToOne
  @JoinColumn(name = "supposed_bidder_id_fk", referencedColumnName = "user_id")
  public UserEntity getUserBySupposedBidderIdFk() {
    return userBySupposedBidderIdFk;
  }

  public void setUserBySupposedBidderIdFk(UserEntity userBySupposedBidderIdFk) {
    this.userBySupposedBidderIdFk = userBySupposedBidderIdFk;
  }

  @ManyToOne
  @JoinColumn(name = "status_id_fk", referencedColumnName = "status_id", nullable = false)
  public StatusTypeEntity getStatusTypeByStatusIdFk() {
    return statusTypeByStatusIdFk;
  }

  public void setStatusTypeByStatusIdFk(StatusTypeEntity statusTypeByStatusIdFk) {
    this.statusTypeByStatusIdFk = statusTypeByStatusIdFk;
  }

  @OneToMany(mappedBy = "biddingByBiddingIdFk")
  public Collection<ProductEntity> getProductsByBiddingId() {
    return productsByBiddingId;
  }

  public void setProductsByBiddingId(Collection<ProductEntity> productsByBiddingId) {
    this.productsByBiddingId = productsByBiddingId;
  }

  //  @Override
  //  public String toString() {
  //    return "BiddingEntity{" +
  //        "biddingId=" + biddingId +
  //        ", startingPrice=" + startingPrice +
  //        ", offerEndDate=" + offerEndDate +
  //        ", bestOffer=" + bestOffer +
  //        ", userBySupposedBidderIdFk=" + userBySupposedBidderIdFk +
  //        ", statusTypeByStatusIdFk=" + statusTypeByStatusIdFk +
  //        ", productsByBiddingId=" + productsByBiddingId +
  //        '}';
  //  }
}
