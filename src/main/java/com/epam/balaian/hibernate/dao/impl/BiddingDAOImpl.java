package com.epam.balaian.hibernate.dao.impl;

import com.epam.balaian.hibernate.dao.BiddingDAO;
import com.epam.balaian.hibernate.model.Bidding;
import com.epam.balaian.hibernate.model.StatusType;
import com.epam.balaian.hibernate.model.User;
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
    SessionTerminal.openSessionAndTransaction();

    try {
      SessionTerminal.FACTORY.getCurrentSession().save(bidding);
      return bidding;
    } finally {
      SessionTerminal.closeSessionAndTransaction();
    }
  }

  @Override
  public Bidding getBiddingById(long biddingId) {
    SessionTerminal.openSessionAndTransaction();

    try {
      return SessionTerminal.FACTORY.getCurrentSession().get(Bidding.class, biddingId);
    } finally {
      SessionTerminal.closeSessionAndTransaction();
    }
  }

  @Override
  public Bidding editBiddingBestOfferAndBidder(User supposedBidder, Double bestOffer,
      long biddingId) {
    SessionTerminal.openSessionAndTransaction();

    try{
      Bidding biddingToUpdate =
          SessionTerminal.FACTORY.getCurrentSession().get(Bidding.class, biddingId);
      biddingToUpdate.setBestOffer(bestOffer);
      biddingToUpdate.setUserAsSupposedBidder(supposedBidder);
      return biddingToUpdate;
    }finally{
      SessionTerminal.closeSessionAndTransaction();
    }
  }

  @Override
  public Bidding editBidding(
      Double startingPrice, Date offerEndDate, StatusType status, long biddingId) {
    SessionTerminal.openSessionAndTransaction();

    try {
      Bidding biddingToUpdate =
          SessionTerminal.FACTORY.getCurrentSession().get(Bidding.class, biddingId);
      biddingToUpdate.setStartingPrice(startingPrice);
      biddingToUpdate.setOfferEndDate(offerEndDate);
      biddingToUpdate.setBiddingStatus(status);
      SessionTerminal.FACTORY.getCurrentSession().update(biddingToUpdate);
      return biddingToUpdate;
    } finally {
      SessionTerminal.closeSessionAndTransaction();
    }
  }

  @Override
  public Bidding editBiddingStartingPriceAndOfferEndDate(
      Double startingPrice, Date offerEndDate, long biddingId) {
    SessionTerminal.openSessionAndTransaction();

    try {
      Bidding biddingToUpdate =
          SessionTerminal.FACTORY.getCurrentSession().get(Bidding.class, biddingId);
      biddingToUpdate.setStartingPrice(startingPrice);
      biddingToUpdate.setOfferEndDate(offerEndDate);
      SessionTerminal.FACTORY.getCurrentSession().update(biddingToUpdate);
      return biddingToUpdate;
    } finally {
      SessionTerminal.closeSessionAndTransaction();
    }
  }

  @Override
  public Bidding deleteBidding(long biddingId) {
    SessionTerminal.openSessionAndTransaction();

    try {
      Bidding biddingToRemove =
          SessionTerminal.FACTORY.getCurrentSession().get(Bidding.class, biddingId);
      SessionTerminal.FACTORY.getCurrentSession().delete(biddingToRemove);
      return biddingToRemove;
    } finally {
      SessionTerminal.closeSessionAndTransaction();
    }
  }

  @Override
  public List<Bidding> getAllBidding() {
    SessionTerminal.openSessionAndTransaction();

    try {
      return SessionTerminal.FACTORY.getCurrentSession().createQuery("from Bidding").list();

    } finally {
      SessionTerminal.closeSessionAndTransaction();
    }
  }

  @Override
  public List<Bidding> getAllBiddingByIdSupposedBidder(long idSupposedBidder) {
    SessionTerminal.openSessionAndTransaction();

    try {
      return SessionTerminal.FACTORY
          .getCurrentSession()
          .createQuery("from Bidding where biddingId = " + idSupposedBidder)
          .list();
    } finally {
      SessionTerminal.openSessionAndTransaction();
    }
  }
}
