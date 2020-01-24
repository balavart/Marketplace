package com.epam.balaian.hibernate.dao.service;

import com.epam.balaian.hibernate.dao.BiddingEntityDAO;
import com.epam.balaian.hibernate.model.BiddingEntity;
import com.epam.balaian.hibernate.model.StatusTypeEntity;
import java.sql.Date;
import java.util.Collection;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * @author Vardan Balaian
 * @created 1/24/2020
 * @since 1.8
 */
public class BiddingEntityDAOImpl implements BiddingEntityDAO {
  private final SessionFactory factory;

  public BiddingEntityDAOImpl() {
    this.factory = new Configuration().configure().buildSessionFactory();
  }

  @Override
  public void addBidding(BiddingEntity bidding) {
    final Session session = factory.openSession();
    Transaction tx = session.beginTransaction();

    try {
      session.save(bidding);
    } finally {
      tx.commit();
      session.close();
    }
  }

  @Override
  public BiddingEntity getBiddingById(long biddingId) {

    final Session session = factory.openSession();
    Transaction tx = session.beginTransaction();
    try {
      return (BiddingEntity) session.get(BiddingEntity.class, biddingId);
    } finally {
      tx.commit();
      session.close();
    }
  }

  @Override
  public void editBidding(
      Double startingPrice, Date offerEndDate, StatusTypeEntity status, long biddingId) {
    final Session session = factory.openSession();
    Transaction tx = session.beginTransaction();
    try {
      BiddingEntity biddingToUpdate = session.get(BiddingEntity.class, biddingId);
      biddingToUpdate.setStartingPrice(startingPrice);
      biddingToUpdate.setOfferEndDate(offerEndDate);
      biddingToUpdate.setStatusTypeByStatusIdFk(status);
      session.update(biddingToUpdate);
    } finally {
      tx.commit();
      session.close();
    }
  }

  @Override
  public void deleteBidding(long biddingId) {
    final Session session = factory.openSession();
    Transaction tx = session.beginTransaction();
    try {
      BiddingEntity biddingToRemove = session.get(BiddingEntity.class, biddingId);
      session.delete(biddingToRemove);
    } finally {
      tx.commit();
      session.close();
    }
  }

  @Override
  public Collection<BiddingEntity> getAllBidding() {
    final Session session = factory.openSession();
    Transaction tx = session.beginTransaction();
    try {
      return session.createQuery("from BiddingEntity").list();
    } finally {
      tx.commit();
      session.close();
    }
  }

  @Override
  public Collection<BiddingEntity> getAllBiddingByIdSupposedBidder(long idSupposedBidder) {
    final Session session = factory.openSession();
    Transaction tx = session.beginTransaction();
    try {
      return session.createQuery("from BiddingEntity where biddingId = " + idSupposedBidder).list();
    } finally {
      tx.commit();
      session.close();
    }
  }
}
