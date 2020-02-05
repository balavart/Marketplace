package com.epam.balaian.hibernate;

import com.epam.balaian.hibernate.dao.BiddingDAO;
import com.epam.balaian.hibernate.dao.impl.BiddingDAOImpl;
import com.epam.balaian.hibernate.dao.ProductDAO;
import com.epam.balaian.hibernate.dao.impl.ProductDAOImpl;
import com.epam.balaian.hibernate.dao.RoleDAO;
import com.epam.balaian.hibernate.dao.impl.RoleDAOImpl;
import com.epam.balaian.hibernate.dao.StatusTypeDAO;
import com.epam.balaian.hibernate.dao.impl.StatusTypeDAOImpl;
import com.epam.balaian.hibernate.dao.UserDAO;
import com.epam.balaian.hibernate.dao.impl.UserDAOImpl;
import com.epam.balaian.hibernate.model.Bidding;
import com.epam.balaian.hibernate.model.Product;
import com.epam.balaian.hibernate.model.Role;
import com.epam.balaian.hibernate.model.StatusType;
import com.epam.balaian.hibernate.model.User;
import com.epam.balaian.hibernate.services.SessionTerminal;
import java.sql.Date;

/**
 * @author Vardan Balaian
 * @created 1/24/2020
 * @since 1.8
 */
public class MainHibernate {

  public static void main(String[] args) {

    RoleDAO roleDAO = new RoleDAOImpl();
    Role role = roleDAO.getRoleById(2);

    StatusTypeDAO statusTypeDAO = new StatusTypeDAOImpl();
    StatusType status = statusTypeDAO.getStatusById(2);

    User newUser =
        new User("cat", "caty good", "Cat Notdog", "Catland", "ds@email.com", "+34554322", role);
    UserDAO userDAO = new UserDAOImpl();
    userDAO.addUser(newUser);

//    Bidding newBidding = new Bidding(555.55, Date.valueOf("2021-03-31"), null, null, status);
//    BiddingDAO biddingDAO = new BiddingDAOImpl();
//    biddingDAO.addBidding(newBidding);
//
//    Product newProduct = new Product("headphones", "power and style", newUser, newBidding);
//    ProductDAO productDAO = new ProductDAOImpl();
//    productDAO.addProduct(newProduct);
  }
}
