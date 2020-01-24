package com.epam.balaian.hibernate;

import com.epam.balaian.hibernate.dao.BiddingEntityDAO;
import com.epam.balaian.hibernate.dao.ProductEntityDAO;
import com.epam.balaian.hibernate.dao.UserEntityDAO;
import com.epam.balaian.hibernate.dao.service.BiddingEntityDAOImpl;
import com.epam.balaian.hibernate.dao.service.ProductEntityDAOImpl;
import com.epam.balaian.hibernate.dao.service.UserEntityDAOImpl;

/**
 * @author Vardan Balaian
 * @created 1/24/2020
 * @since 1.8
 */
public class MainHibernate {

  public static void main(String[] args) {
    UserEntityDAO userEntityDAO = new UserEntityDAOImpl();
    //    UserEntity newUser = new UserEntity();
    //    newUser.setLoginName("super_zombie");
    //    newUser.setPassword("zom123");
    //    newUser.setFullName("Zack Sheder");
    //    newUser.setCity("Paris");
    //    newUser.setEmail("fergok@gm.com");
    //    newUser.setPhoneNumber("+53421234");
    //    newUser.setRoleByRoleIdFk(new RoleEntityDAOImpl().getRoleById(2));
    //    userEntityDAO.addUser(newUser); // add user

    ProductEntityDAO productEntityDAO = new ProductEntityDAOImpl();
    //    ProductEntity newProduct = new ProductEntity();
    //    newProduct.setProductTitle("Canvas");
    //    newProduct.setDescription("10 colors have");
    //    newProduct.setUserByProductOwnerIdFk(new UserEntityDAOImpl().getUserById( 1));
    //    newProduct.setBiddingByBiddingIdFk(new BiddingEntityDAOImpl().getBiddingById(3));
    //    productEntityDAO.addProduct(newProduct); // add product

    //    productEntityDAO.deleteProduct(10); // delete product

    //      productEntityDAO.editProduct("Bird", "Little",7); // edit product

    //    System.out.println(productEntityDAO.getAllProducts()); // show all products

    //    System.out.println(productEntityDAO.getAllProductsByIdOwner(3)); // show all products by
    // Owner

    BiddingEntityDAO biddingEntityDAO = new BiddingEntityDAOImpl();
    //    BiddingEntity bidding = new BiddingEntity();
    //    bidding.setStartingPrice(555.55);
    //    bidding.setOfferEndDate(Date.valueOf("2021-03-31"));
    //    bidding.setBestOffer(null);
    //    bidding.setUserBySupposedBidderIdFk(null);
    //    bidding.setStatusTypeByStatusIdFk(new StatusTypeEntityDAOImpl().getStatusById(1));
    //    biddingEntityDAO.addBidding(bidding); // add bidding

    //    biddingEntityDAO.deleteBidding(6); // delete bidding

    //    biddingEntityDAO.editBidding(
    //        111.11, Date.valueOf("2023-01-13"), new StatusTypeEntityDAOImpl().getStatusById(2),
    // 7); // edit bidding

    //        System.out.println(biddingEntityDAO.getAllBidding()); // show all bidding

    //    System.out.println(biddingEntityDAO.getAllBiddingByIdSupposedBidder(1)); // show all
    // bidding by Supposed Bidder

    // todo:change lazy connection and add tests
  }
}
