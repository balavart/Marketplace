package com.epam.balaian.jdbc.dao;

import com.epam.balaian.jdbc.model.Bidding;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Vardan_Balaian
 * @created 1/22/2020
 * @since 1.8
 */
public interface BiddingDAO {

  void addBidding(Bidding bidding) throws SQLException;

  Bidding getByIdBidding(long idBidding) throws SQLException;

  void updateBidding(double startingPrice, Date offerEndDate, int idStatus, long idBidding)
      throws SQLException;

  void deleteBidding(long idBidding) throws SQLException;

  List<Bidding> getAllBidding() throws SQLException;

  List<Bidding> getAllBiddingByIdSupposedBidder(final long idSupposedBidder) throws SQLException;
}
