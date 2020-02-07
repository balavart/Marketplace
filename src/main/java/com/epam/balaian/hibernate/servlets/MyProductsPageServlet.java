package com.epam.balaian.hibernate.servlets;

import com.epam.balaian.hibernate.dao.BiddingDAO;
import com.epam.balaian.hibernate.dao.ProductDAO;
import com.epam.balaian.hibernate.dao.impl.BiddingDAOImpl;
import com.epam.balaian.hibernate.dao.impl.ProductDAOImpl;
import com.epam.balaian.hibernate.model.Bidding;
import com.epam.balaian.hibernate.model.Product;
import com.epam.balaian.hibernate.model.User;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Vardan Balaian
 * @created 2/4/2020
 * @since 1.8
 */
public class MyProductsPageServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    ProductDAO productDAO = new ProductDAOImpl();

    User loggedUser = (User) req.getSession().getAttribute("loggedUser");
    long loggedUserID = loggedUser.getUserId();
    List<Product> userProductList = productDAO.getAllProductsByIdOwner(loggedUserID);
    int userProductNumber = userProductList.size();

    req.setAttribute("userProductList", userProductList);
    req.setAttribute("userProductNumber", userProductNumber);

    req.getServletContext().getRequestDispatcher("/jsp/my_products.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    if (Objects.nonNull(req.getParameter("hiddenUserProductID"))) {
      long userProductID = Long.parseLong(req.getParameter("hiddenUserProductID"));
      req.getSession().setAttribute("userProductID", userProductID);
      resp.sendRedirect("product_editing");
    }else if (Objects.nonNull(req.getParameter("hiddenDeleteProduct"))){
      //123
    }else if (Objects.nonNull(req.getParameter("hiddenProductAdding"))){
      resp.sendRedirect("product_adding");
    }
  }
}
