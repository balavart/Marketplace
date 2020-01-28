package com.epam.balaian.hibernate.dao;

import com.epam.balaian.hibernate.model.Bidding;
import com.epam.balaian.hibernate.model.StatusType;
import java.sql.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * @author Vardan Balaian
 * @created 1/24/2020
 * @since 1.8
 */
public class BiddingDAOImpl implements BiddingDAO {
  private final SessionFactory factory;

  public BiddingDAOImpl() {
    this.factory = new Configuration().configure().buildSessionFactory();
  }

  @Override
  public Bidding addBidding(Bidding bidding) {
    final Session session = factory.openSession();
    Transaction tx = session.beginTransaction();

    try {
      session.save(bidding);
      return bidding;
    } finally {
      tx.commit();
      session.close();
    }
  }

  @Override
  public Bidding getBiddingById(long biddingId) {

    final Session session = factory.openSession();
    Transaction tx = session.beginTransaction();
    try {
      return (Bidding) session.get(Bidding.class, biddingId);
    } finally {
      tx.commit();
      session.close();
    }
  }

  @Override
  public Bidding editBidding(
      Double startingPrice, Date offerEndDate, StatusType status, long biddingId) {
    final Session session = factory.openSession();
    Transaction tx = session.beginTransaction();
    try {
      Bidding biddingToUpdate = session.get(Bidding.class, biddingId);
      biddingToUpdate.setStartingPrice(startingPrice);
      biddingToUpdate.setOfferEndDate(offerEndDate);
      biddingToUpdate.setBiddingStatus(status);
      session.update(biddingToUpdate);
      return biddingToUpdate;
    } finally {
      tx.commit();
      session.close();
    }
  }

  @Override
  public Bidding deleteBidding(long biddingId) {
    final Session session = factory.openSession();
    Transaction tx = session.beginTransaction();
    try {
      Bidding biddingToRemove = session.get(Bidding.class, biddingId);
      session.delete(biddingToRemove);
      return biddingToRemove;
    } finally {
      tx.commit();
      session.close();
    }
  }

  @Override
  public List<Bidding> getAllBidding() {
    final Session session = factory.openSession();
    Transaction tx = session.beginTransaction();
    try {
      return session.createQuery("from Bidding").list();
    } finally {
      tx.commit();
      session.close();
    }
  }

  @Override
  public List<Bidding> getAllBiddingByIdSupposedBidder(long idSupposedBidder) {
    final Session session = factory.openSession();
    Transaction tx = session.beginTransaction();
    try {
      return session.createQuery("from Bidding where biddingId = " + idSupposedBidder).list();
    } finally {
      tx.commit();
      session.close();
    }
  }
}
