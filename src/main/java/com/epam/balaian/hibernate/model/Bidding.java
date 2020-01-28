package com.epam.balaian.hibernate.model;

import java.sql.Date;
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
@Table(name = "bidding", schema = "marketplace")
public class Bidding {

  private Long biddingId;
  private Double startingPrice;
  private Date offerEndDate;
  private Double bestOffer;
  private User userAsSupposedBidder;
  private StatusType biddingStatus;
  private Product productByBidding;

  public Bidding(Long biddingId) {
    this.biddingId = biddingId;
  }

  public Bidding() {}

  public Bidding(Double startingPrice, Date offerEndDate, StatusType status, long biddingId) {
    this.startingPrice = startingPrice;
    this.offerEndDate = offerEndDate;
    this.biddingStatus = status;
    this.biddingId = biddingId;
  }

  public Bidding(
      Double startingPrice,
      Date offerEndDate,
      Double bestOffer,
      User userAsSupposedBidder,
      StatusType biddingStatus) {
    this.startingPrice = startingPrice;
    this.offerEndDate = offerEndDate;
    this.bestOffer = bestOffer;
    this.userAsSupposedBidder = userAsSupposedBidder;
    this.biddingStatus = biddingStatus;
  }

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
  public User getUserAsSupposedBidder() {
    return userAsSupposedBidder;
  }

  public void setUserAsSupposedBidder(User userAsSupposedBidder) {
    this.userAsSupposedBidder = userAsSupposedBidder;
  }

  @ManyToOne
  @JoinColumn(name = "status_id_fk", referencedColumnName = "status_id", nullable = false)
  public StatusType getBiddingStatus() {
    return biddingStatus;
  }

  public void setBiddingStatus(StatusType biddingStatus) {
    this.biddingStatus = biddingStatus;
  }

  @OneToOne(mappedBy = "biddingByProduct")
  public Product getProductByBidding() {
    return productByBidding;
  }

  public void setProductByBidding(Product productByBidding) {
    this.productByBidding = productByBidding;
  }

  @Override
  public String toString() {
    return "Bidding{"
        + "biddingId="
        + biddingId
        + ", startingPrice="
        + startingPrice
        + ", offerEndDate="
        + offerEndDate
        + ", bestOffer="
        + bestOffer
        + ", userBySupposedBidderIdFk="
        + userAsSupposedBidder
        + ", statusTypeByStatusIdFk="
        + biddingStatus
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
    Bidding bidding = (Bidding) o;
    return Objects.equals(biddingId, bidding.biddingId)
        && Objects.equals(startingPrice, bidding.startingPrice)
        && Objects.equals(offerEndDate, bidding.offerEndDate)
        && Objects.equals(bestOffer, bidding.bestOffer)
        && Objects.equals(userAsSupposedBidder, bidding.userAsSupposedBidder)
        && Objects.equals(biddingStatus, bidding.biddingStatus)
        && Objects.equals(productByBidding, bidding.productByBidding);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        biddingId,
        startingPrice,
        offerEndDate,
        bestOffer,
        userAsSupposedBidder,
        biddingStatus,
        productByBidding);
  }
}
