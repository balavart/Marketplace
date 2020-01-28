package com.epam.balaian.hibernate.dao;

import com.epam.balaian.hibernate.model.Bidding;
import com.epam.balaian.hibernate.model.StatusType;
import com.epam.balaian.hibernate.services.SessionTerminal;
import java.sql.Date;
import java.util.List;

/**
 * @author Vardan Balaian
 * @created 1/24/2020
 * @since 1.8
 */
public class BiddingDAOImpl implements BiddingDAO {

  @Override
  public Bidding addBidding(Bidding bidding) {
    SessionTerminal.factory.getCurrentSession().save(bidding);
    return bidding;
  }

  @Override
  public Bidding getBiddingById(long biddingId) {
    return (Bidding) SessionTerminal.factory.getCurrentSession().get(Bidding.class, biddingId);
  }

  @Override
  public Bidding editBidding(
      Double startingPrice, Date offerEndDate, StatusType status, long biddingId) {
    Bidding biddingToUpdate =
        SessionTerminal.factory.getCurrentSession().get(Bidding.class, biddingId);
    biddingToUpdate.setStartingPrice(startingPrice);
    biddingToUpdate.setOfferEndDate(offerEndDate);
    biddingToUpdate.setBiddingStatus(status);
    SessionTerminal.factory.getCurrentSession().update(biddingToUpdate);
    return biddingToUpdate;
  }

  @Override
  public Bidding deleteBidding(long biddingId) {
    Bidding biddingToRemove =
        SessionTerminal.factory.getCurrentSession().get(Bidding.class, biddingId);
    SessionTerminal.factory.getCurrentSession().delete(biddingToRemove);
    return biddingToRemove;
  }

  @Override
  public List<Bidding> getAllBidding() {
    return SessionTerminal.factory.getCurrentSession().createQuery("from Bidding").list();
  }

  @Override
  public List<Bidding> getAllBiddingByIdSupposedBidder(long idSupposedBidder) {
    return SessionTerminal.factory
        .getCurrentSession()
        .createQuery("from Bidding where biddingId = " + idSupposedBidder)
        .list();
  }
}
