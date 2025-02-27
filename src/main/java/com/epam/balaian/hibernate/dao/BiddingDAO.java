package com.epam.balaian.hibernate.dao;

import com.epam.balaian.hibernate.model.Bidding;
import com.epam.balaian.hibernate.model.StatusType;
import com.epam.balaian.hibernate.model.User;
import java.sql.Date;
import java.util.List;

/**
 * @author Vardan Balaian
 * @created 1/24/2020
 * @since 1.8
 */
public interface BiddingDAO {

  Bidding addBidding(Bidding bidding);

  Bidding getBiddingById(long biddingId);

  Bidding editBidding(Double startingPrice, Date offerEndDate, StatusType status, long biddingId);

  void editBiddingBestOfferAndBidder(
      User supposedBidder, Double bestOffer, long biddingId); // todo: add test

  Bidding deleteBidding(long biddingId);

  List<Bidding> getAllBidding();

  List<Bidding> getAllBiddingByIdSupposedBidder(long idSupposedBidder);
}
