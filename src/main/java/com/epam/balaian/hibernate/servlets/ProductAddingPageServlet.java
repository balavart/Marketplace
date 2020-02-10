package com.epam.balaian.hibernate.servlets;

import com.epam.balaian.hibernate.dao.BiddingDAO;
import com.epam.balaian.hibernate.dao.ProductDAO;
import com.epam.balaian.hibernate.dao.StatusTypeDAO;
import com.epam.balaian.hibernate.dao.UserDAO;
import com.epam.balaian.hibernate.dao.impl.BiddingDAOImpl;
import com.epam.balaian.hibernate.dao.impl.ProductDAOImpl;
import com.epam.balaian.hibernate.dao.impl.StatusTypeDAOImpl;
import com.epam.balaian.hibernate.dao.impl.UserDAOImpl;
import com.epam.balaian.hibernate.model.Bidding;
import com.epam.balaian.hibernate.model.Product;
import com.epam.balaian.hibernate.model.StatusType;
import com.epam.balaian.hibernate.model.User;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Vardan Balaian
 * @created 2/7/2020
 * @since 1.8
 */
public class ProductAddingPageServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Date currentDate = new Date(System.currentTimeMillis());
    req.setAttribute("currentDate", currentDate);

    req.getServletContext().getRequestDispatcher("/jsp/product_adding.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    ProductDAO productDAO = new ProductDAOImpl();
    BiddingDAO biddingDAO = new BiddingDAOImpl();
    StatusTypeDAO statusTypeDAO = new StatusTypeDAOImpl();

    String newProductName = req.getParameter("sale");
    String newDescription = req.getParameter("description");
    User loggedUser = (User) req.getSession().getAttribute("loggedUser");
    Double newStartingPrice = Double.valueOf(req.getParameter("startprice"));
    Date newEndDate = Date.valueOf(req.getParameter("end_date"));
    String status = req.getParameter("status");

    StatusType newStatusType = statusTypeDAO.getStatusByTitle(status);
    Bidding newBidding =
        biddingDAO.addBidding(new Bidding(newStartingPrice, newEndDate,newStartingPrice,loggedUser, newStatusType));
    productDAO.addProduct(new Product(newProductName, newDescription, loggedUser, newBidding));

    resp.sendRedirect("my_products");
  }
}
