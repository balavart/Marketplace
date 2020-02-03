package com.epam.balaian.jdbc.dao;

/**
 * @author Vardan_Balaian
 * @created 1/23/2020
 * @since 1.8
 */
public interface BDScheme {

  String USER_TABLE = "marketplace.user";
  String LOGIN_NAME = "marketplace.user.login_name";
  String PASSWORD = "marketplace.user.password";
  String FULL_NAME = "marketplace.user.full_name";
  String CITY = "marketplace.user.city";
  String EMAIL = "marketplace.user.email";
  String PHONE_NUMBER = "marketplace.user.phone_number";
  String ID_ROLE_FK = "marketplace.user.role_id_fk";

  String PRODUCT_TABLE = "marketplace.product";
  String ID_PRODUCT = "marketplace.product.product_id";
  String PRODUCT_TITLE = "marketplace.product.product_title";
  String DESCRIPTION = "marketplace.product.description";
  String ID_PRODUCT_OWNER_FK = "marketplace.product.product_owner_id_fk";
  String ID_BIDDING_FK = "marketplace.product.bidding_id_fk";

  String BIDDING_TABLE = "marketplace.bidding";
  String ID_BIDDING = "marketplace.bidding.bidding_id";
  String STARTING_PRICE = "marketplace.bidding.starting_price";
  String OFFER_END_DATE = "marketplace.bidding.offer_end_date";
  String BEST_OFFER = "marketplace.bidding.best_offer";
  String ID_SUPPOSED_BIDDER_FK = "marketplace.bidding.supposed_bidder_id_fk";
  String ID_STATUS_FK = "marketplace.bidding.status_id_fk";
}
