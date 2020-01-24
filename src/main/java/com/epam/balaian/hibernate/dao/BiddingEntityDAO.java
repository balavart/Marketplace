package com.epam.balaian.hibernate.dao;

import com.epam.balaian.hibernate.model.BiddingEntity;
import com.epam.balaian.hibernate.model.StatusTypeEntity;
import java.sql.Date;
import java.util.Collection;

/**
 * @author Vardan Balaian
 * @created 1/24/2020
 * @since 1.8
 */
public interface BiddingEntityDAO {

  void addBidding(BiddingEntity bidding);

  BiddingEntity getBiddingById(long biddingId);

  void editBidding(
      Double startingPrice, Date offerEndDate, StatusTypeEntity status, long biddingId);

  void deleteBidding(long biddingId);

  Collection<BiddingEntity> getAllBidding();

  Collection<BiddingEntity> getAllBiddingByIdSupposedBidder(final long idSupposedBidder);
}
