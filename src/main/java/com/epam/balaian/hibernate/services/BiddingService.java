package com.epam.balaian.hibernate.services;

import com.epam.balaian.hibernate.dao.BiddingDAO;
import com.epam.balaian.hibernate.model.Bidding;
import com.epam.balaian.hibernate.model.User;
import java.util.List;

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

  public boolean checkBiddingAddition(Bidding bidding) {
    Bidding addedBidding = biddingDAO.addBidding(bidding);

    return addedBidding != null;
  }

  public boolean checkBiddingPresence(Bidding bidding) {
    Bidding existingBidding = biddingDAO.getBiddingById(bidding.getBiddingId());

    return existingBidding != null;
  }

  public boolean checkBiddingEditing(Bidding bidding) {
    Bidding editedBidding =
        biddingDAO.editBidding(
            bidding.getStartingPrice(),
            bidding.getOfferEndDate(),
            bidding.getBiddingStatus(),
            bidding.getBiddingId());

    return editedBidding != null;
  }

  public boolean checkBiddingRemoval(Bidding bidding) {
    Bidding deletedBidding = biddingDAO.deleteBidding(bidding.getBiddingId());

    return deletedBidding != null;
  }

  public boolean checkAllBiddingPresence(List<Bidding> allExistingBidding) {
    allExistingBidding = biddingDAO.getAllBidding();

    return allExistingBidding != null;
  }

  public boolean checkAllBiddingPresenceByIdSupposedBidder(
      List<Bidding> allExistingBidding, User supposedBidder) {
    allExistingBidding = biddingDAO.getAllBiddingByIdSupposedBidder(supposedBidder.getUserId());

    return allExistingBidding != null;
  }
}
