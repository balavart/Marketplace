package com.epam.balaian.jdbc.model;

import java.sql.Date;

/**
 * @author Vardan_Balaian
 * @created 1/22/2020
 * @since 1.8
 */
public class Bidding {

  private long idBidding;
  private double startingPrice;
  private Date offerEndDate;
  private Double bestOffer;
  private Long idSupposedBidder;
  private int idStatus;

  public Bidding() {}

  public Bidding(
      long idBidding,
      double startingPrice,
      Date offerEndDate,
      Double bestOffer,
      Long idSupposedBidder,
      int idStatus) {
    this.idBidding = idBidding;
    this.startingPrice = startingPrice;
    this.offerEndDate = offerEndDate;
    this.bestOffer = bestOffer;
    this.idSupposedBidder = idSupposedBidder;
    this.idStatus = idStatus;
  }

  public long getIdBidding() {
    return idBidding;
  }

  public void setIdBidding(long idBidding) {
    this.idBidding = idBidding;
  }

  public double getStartingPrice() {
    return startingPrice;
  }

  public void setStartingPrice(double startingPrice) {
    this.startingPrice = startingPrice;
  }

  public Date getOfferEndDate() {
    return offerEndDate;
  }

  public void setOfferEndDate(Date offerEndDate) {
    this.offerEndDate = offerEndDate;
  }

  public Double getBestOffer() {
    return bestOffer;
  }

  public void setBestOffer(Double bestOffer) {
    this.bestOffer = bestOffer;
  }

  public Long getIdSupposedBidder() {
    return idSupposedBidder;
  }

  public void setIdSupposedBidder(Long idSupposedBidder) {
    this.idSupposedBidder = idSupposedBidder;
  }

  public int getIdStatus() {
    return idStatus;
  }

  public void setIdStatus(int idStatus) {
    this.idStatus = idStatus;
  }

  @Override
  public String toString() {
    return "Bidding{" +
        "idBidding=" + idBidding +
        ", startingPrice=" + startingPrice +
        ", offerEndDate=" + offerEndDate +
        ", bestOffer=" + bestOffer +
        ", idSupposedBidder=" + idSupposedBidder +
        ", idStatus=" + idStatus +
        '}';
  }
}
