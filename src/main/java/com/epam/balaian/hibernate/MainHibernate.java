package com.epam.balaian.hibernate;

import com.epam.balaian.hibernate.dao.BiddingDAO;
import com.epam.balaian.hibernate.dao.BiddingDAOImpl;
import com.epam.balaian.hibernate.dao.ProductDAO;
import com.epam.balaian.hibernate.dao.ProductDAOImpl;
import com.epam.balaian.hibernate.dao.UserDAO;
import com.epam.balaian.hibernate.dao.UserDAOImpl;

/**
 * @author Vardan Balaian
 * @created 1/24/2020
 * @since 1.8
 */
public class MainHibernate {

  public static void main(String[] args) {
    UserDAO userDAO = new UserDAOImpl();
    //    User newUser = new User();
    //    newUser.setLoginName("super_zombie");
    //    newUser.setPassword("zom123");
    //    newUser.setFullName("Zack Sheder");
    //    newUser.setCity("Paris");
    //    newUser.setEmail("fergok@gm.com");
    //    newUser.setPhoneNumber("+53421234");
    //    newUser.setUserRole(new RoleDAOImpl().getRoleById(2));
    //    userDAO.addUser(newUser); // add user

    ProductDAO productDAO = new ProductDAOImpl();
    //    Product newProduct = new Product();
    //    newProduct.setProductTitle("Canvas");
    //    newProduct.setDescription("10 colors have");
    //    newProduct.setProductOwner(newUser);
    //    newProduct.setBiddingByProduct(new BiddingDAOImpl().getBiddingById(3));
    //    productDAO.addProduct(newProduct); // add product

    //        productDAO.deleteProduct(10); // delete product
    //
    //          productDAO.editProduct("Bird", "Little",7); // edit product
    //
    //        System.out.println(productDAO.getAllProducts()); // show all products
    //
    //        System.out.println(productDAO.getAllProductsByIdOwner(3)); // show all products by
    // Owner

    BiddingDAO biddingDAO = new BiddingDAOImpl();
    //    Bidding bidding = new Bidding();
    //    bidding.setStartingPrice(555.55);
    //    bidding.setOfferEndDate(Date.valueOf("2021-03-31"));
    //    bidding.setBestOffer(null);
    //    bidding.setUserBySupposedBidderIdFk(null);
    //    bidding.setStatusTypeByStatusIdFk(new StatusTypeDAOImpl().getStatusById(1));
    //    biddingDAO.addBidding(bidding); // add bidding

    //    biddingDAO.deleteBidding(6); // delete bidding

    //    biddingDAO.editBidding(
    //        111.11, Date.valueOf("2023-01-13"), new StatusTypeDAOImpl().getStatusById(2),
    // 7); // edit bidding

    //            System.out.println(biddingDAO.getAllBidding()); // show all bidding

    //        System.out.println(biddingDAO.getAllBiddingByIdSupposedBidder(1)); // show all
    // bidding by Supposed Bidder
  }
}
