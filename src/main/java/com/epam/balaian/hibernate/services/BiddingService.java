package com.epam.balaian.hibernate.services;

import com.epam.balaian.hibernate.dao.BiddingDAO;
import com.epam.balaian.hibernate.model.Bidding;

/**
 * @author Vardan Balaian
 * @created 28.01.2020
 * @since 1.8
 */
public class BiddingService {

  private BiddingDAO biddingDAO;

  public BiddingService(BiddingDAO biddingDAO) {
    this.biddingDAO = biddingDAO;
  }

  public boolean checkBiddingPresence(Bidding bidding) {
    Bidding biddingExample = biddingDAO.getBiddingById(bidding.getBiddingId());

    return biddingExample != null;
  }
}
