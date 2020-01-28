package com.epam.balaian.jdbc.dao;

import static com.epam.balaian.jdbc.dao.BDScheme.BEST_OFFER;
import static com.epam.balaian.jdbc.dao.BDScheme.BIDDING_TABLE;
import static com.epam.balaian.jdbc.dao.BDScheme.ID_BIDDING;
import static com.epam.balaian.jdbc.dao.BDScheme.ID_STATUS_FK;
import static com.epam.balaian.jdbc.dao.BDScheme.ID_SUPPOSED_BIDDER_FK;
import static com.epam.balaian.jdbc.dao.BDScheme.OFFER_END_DATE;
import static com.epam.balaian.jdbc.dao.BDScheme.STARTING_PRICE;

import com.epam.balaian.jdbc.model.Bidding;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Vardan_Balaian
 * @created 23.01.2020
 * @since 1.8
 */
public class BiddingDAOImpl implements BiddingDAO {

  @Override
  public void addBidding(Bidding bidding) throws SQLException {

    String sqlBiddingInsert =
        String.format(
            "INSERT INTO %s (%s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?)",
            BIDDING_TABLE,
            STARTING_PRICE,
            OFFER_END_DATE,
            BEST_OFFER,
            ID_SUPPOSED_BIDDER_FK,
            ID_STATUS_FK);

    try (Connection connection = DAOFactoryImpl.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlBiddingInsert)) {
      System.out.println("Connection established");

      preparedStatement.setDouble(1, bidding.getStartingPrice());
      preparedStatement.setDate(2, bidding.getOfferEndDate());
      preparedStatement.setObject(3, bidding.getBestOffer());
      preparedStatement.setObject(4, bidding.getIdSupposedBidder());
      preparedStatement.setInt(5, bidding.getIdStatus());

      preparedStatement.execute();
    }
  }

  @Override
  public Bidding getByIdBidding(long idBidding) throws SQLException {

    String sqlBiddingSelectOne =
        String.format(
            "SELECT %s, %s, %s, %s, %s FROM %s WHERE %s = ?",
            STARTING_PRICE,
            OFFER_END_DATE,
            BEST_OFFER,
            ID_SUPPOSED_BIDDER_FK,
            ID_STATUS_FK,
            BIDDING_TABLE,
            ID_BIDDING);

    try (Connection connection = DAOFactoryImpl.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlBiddingSelectOne)) {
      preparedStatement.setLong(1, idBidding);
      ResultSet rs = preparedStatement.executeQuery();
      Bidding biddingInfo = null;
      while (rs.next()) {
        double startingPrice = rs.getDouble(1);
        Date offerEndDate = rs.getDate(2);
        double bestOffer = rs.getDouble(3);
        long idSupposedBidder = rs.getLong(4);
        int idStatus = rs.getInt(5);
        biddingInfo =
            new Bidding(
                idBidding, startingPrice, offerEndDate, bestOffer, idSupposedBidder, idStatus);
      }
      return biddingInfo;
    }
  }

  @Override
  public void updateBidding(double startingPrice, Date offerEndDate, int status, long idBidding)
      throws SQLException {

    String sqlBiddingUpdate =
        String.format(
            "UPDATE %s SET %s = ?, %s = ?, %s = ? WHERE %s = ?",
            BIDDING_TABLE, STARTING_PRICE, OFFER_END_DATE, ID_STATUS_FK, ID_BIDDING);

    try (Connection connection = DAOFactoryImpl.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlBiddingUpdate)) {
      System.out.println("Connection established");

      preparedStatement.setDouble(1, startingPrice);
      preparedStatement.setDate(2, offerEndDate);
      preparedStatement.setInt(3, status);
      preparedStatement.setLong(4, idBidding);

      int updatedValues = preparedStatement.executeUpdate();
      System.out.println("Values updated: " + updatedValues);
    }
  }

  @Override
  public void deleteBidding(long idBidding) throws SQLException {

    String sqlBiddingDelete =
        String.format("DELETE FROM %s WHERE %s = ?", BIDDING_TABLE, ID_BIDDING);

    try (Connection connection = DAOFactoryImpl.getConnection();
        PreparedStatement preparedStatement =
            connection.prepareStatement(
                sqlBiddingDelete)) {
      System.out.println("Connection established");

      preparedStatement.setLong(1, idBidding);
      preparedStatement.execute();
    }
  }

  @Override
  public List<Bidding> getAllBidding() throws SQLException {

    List<Bidding> biddingList = new ArrayList<>();

    String sqlBiddingSelectAll =
        String.format(
            "SELECT %s, %s, %s, %s, %s, %s FROM %s ",
            ID_BIDDING,
            STARTING_PRICE,
            OFFER_END_DATE,
            BEST_OFFER,
            ID_SUPPOSED_BIDDER_FK,
            ID_STATUS_FK,
            BIDDING_TABLE);

    try (Connection connection = DAOFactoryImpl.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlBiddingSelectAll)) {
      System.out.println("Connection established");

      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        long idBidding = resultSet.getLong(1);
        double startingPrice = resultSet.getDouble(2);
        Date offerEndDate = resultSet.getDate(3);
        Double bestOffer = resultSet.getDouble(4);
        Long idSupposedBidder = resultSet.getLong(5);
        int idStatus = resultSet.getInt(6);
        biddingList.add(
            new Bidding(
                idBidding, startingPrice, offerEndDate, bestOffer, idSupposedBidder, idStatus));
      }
      return biddingList;
    }
  }

  @Override
  public List<Bidding> getAllBiddingByIdSupposedBidder(final long idSupposedBidder)
      throws SQLException {
    return getAllBidding().stream()
        .filter(bidding -> bidding.getIdSupposedBidder() == idSupposedBidder)
        .collect(Collectors.toList());
  }
}
