package com.epam.balaian.jdbc;

import com.epam.balaian.jdbc.dao.BiddingDAO;
import com.epam.balaian.jdbc.dao.DAOFactory;
import com.epam.balaian.jdbc.dao.service.DAOFactoryImpl;
import com.epam.balaian.jdbc.dao.ProductDAO;
import com.epam.balaian.jdbc.dao.UserDAO;
import java.sql.SQLException;

/**
 * @author Vardan_Balaian
 * @created 1/21/2020
 * @since 1.8
 */
public class Main {

  public static void main(String[] args) throws SQLException {
    DAOFactory factory = DAOFactoryImpl.getInstance();

    UserDAO userDAO = factory.getUserDAO();
    //        User newUser = new User();
    //        newUser.setLoginName("new_user2");
    //        newUser.setPassword("user12345");
    //        newUser.setFullName("Igor Birov");
    //        newUser.setCity("Saratov");
    //        newUser.setEmail("eger123@ya.ru");
    //        newUser.setPhoneNumber("89657757743");
    //        newUser.setIdRole(2);
    //        userDAO.addUser(newUser); // add user

    ProductDAO productDAO = factory.getProductDAO();
    //        Product product = new Product();
    //        product.setTitle("Book2");
    //        product.setDescription("Tragedy about programmers");
    //        product.setIdProductOwner(1);
    //        product.setIdBidding(1);
    //        productDAO.addProduct(product); // add product

    //        System.out.println(productDAO.getByIdProduct(2)); // show product by idProduct

    //    productDAO.updateProduct("Shirt", "Nice to wear", 6); // update product

    //        productDAO.deleteProduct(9);// delete product

    //        System.out.println(productDAO.getAllProducts()); // show all products

    //        System.out.println(productDAO.getAllProductsByIdOwner(1)); // show all products by
    // idUser

    BiddingDAO biddingDAO = factory.getBiddingDAO();
    //            Bidding newBidding = new Bidding();
    //            newBidding.setStartingPrice(111.33);
    //            newBidding.setOfferEndDate(Date.valueOf("2021-03-31"));
    //            newBidding.setBestOffer(null);
    //            newBidding.setIdSupposedBidder(null);
    //            newBidding.setIdStatus(1);
    //            biddingDAO.addBidding(newBidding); // add bidding

    //    System.out.println(biddingDAO.getByIdBidding(4)); // show bidding by idBidding

    //    biddingDAO.updateBidding(123.33, Date.valueOf("2020-11-31"),2,4);

    //    biddingDAO.deleteBidding(5);

    //        System.out.println(biddingDAO.getAllBidding()); // show all bidding

    //    System.out.println(
    //        biddingDAO.getAllBiddingByIdSupposedBidder(1)); // show all products by Id Supposed
    // Bidder

    // todo: add tests
  }
}
