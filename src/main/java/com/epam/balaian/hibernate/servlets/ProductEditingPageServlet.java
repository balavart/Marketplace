package com.epam.balaian.hibernate.servlets;

import com.epam.balaian.hibernate.dao.BiddingDAO;
import com.epam.balaian.hibernate.dao.ProductDAO;
import com.epam.balaian.hibernate.dao.StatusTypeDAO;
import com.epam.balaian.hibernate.dao.impl.BiddingDAOImpl;
import com.epam.balaian.hibernate.dao.impl.ProductDAOImpl;
import com.epam.balaian.hibernate.dao.impl.StatusTypeDAOImpl;
import com.epam.balaian.hibernate.model.Bidding;
import com.epam.balaian.hibernate.model.Product;
import com.epam.balaian.hibernate.model.StatusType;
import java.io.IOException;
import java.sql.Date;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Vardan Balaian
 * @created 2/6/2020
 * @since 1.8
 */
public class ProductEditingPageServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
      ProductDAO productDAO = new ProductDAOImpl();

      long userProductID = (long) req.getSession().getAttribute("userProductID");
      Product existingUserProduct = productDAO.getByProductId(userProductID);
      Bidding existingUserBidding = existingUserProduct.getBiddingByProduct();

      req.getSession().setAttribute("existingUserProduct", existingUserProduct);
      req.getSession().setAttribute("existingUserBidding", existingUserBidding);

      req.getServletContext().getRequestDispatcher("/jsp/product_editing.jsp").forward(req, resp);

  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    ProductDAO productDAO = new ProductDAOImpl();
    BiddingDAO biddingDAO = new BiddingDAOImpl();
    StatusTypeDAO statusTypeDAO = new StatusTypeDAOImpl();

    Product existingUserProduct = (Product) req.getSession().getAttribute("existingUserProduct");

    long productID = existingUserProduct.getProductId();
    String productName = req.getParameter("sale");
    String description = req.getParameter("description");
    long biddingID = existingUserProduct.getBiddingByProduct().getBiddingId();
    Double startingPrice = Double.valueOf(req.getParameter("startprice"));
    Date endDate = Date.valueOf(req.getParameter("end_date"));
    String status = req.getParameter("status");

    productDAO.editProduct(productName, description, productID);
    StatusType statusType = statusTypeDAO.getStatusByTitle(status);
    biddingDAO.editBidding(startingPrice, endDate, statusType, biddingID);

    resp.sendRedirect("my_products");
  }
}
