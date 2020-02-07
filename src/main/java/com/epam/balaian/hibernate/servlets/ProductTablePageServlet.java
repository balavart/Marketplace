package com.epam.balaian.hibernate.servlets;

import static javafx.scene.input.KeyCode.L;

import com.epam.balaian.hibernate.dao.BiddingDAO;
import com.epam.balaian.hibernate.dao.ProductDAO;
import com.epam.balaian.hibernate.dao.impl.BiddingDAOImpl;
import com.epam.balaian.hibernate.dao.impl.ProductDAOImpl;
import com.epam.balaian.hibernate.model.Product;
import com.epam.balaian.hibernate.model.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Vardan Balaian
 * @created 31.01.2020
 * @since 1.8
 */
public class ProductTablePageServlet extends HttpServlet {
  ProductDAO productDAO = new ProductDAOImpl();

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {
    super.service(req, res);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    User loggedUser = (User) req.getSession().getAttribute("loggedUser");
    List<Product> productList = productDAO.getAllProducts();
    int productNumber = productList.size();
    String relevantStatus = "At auction";
    String soldStatus = "Sold";

    req.getSession().setAttribute("loginName", loggedUser.getLoginName());
    req.setAttribute("productList", productList);
    req.setAttribute("productNumber", productNumber);
    req.getSession().setAttribute("relevantStatus", relevantStatus);
    req.getSession().setAttribute("soldStatus", soldStatus);

    req.getServletContext().getRequestDispatcher("/jsp/product_table.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    BiddingDAO biddingDAO = new BiddingDAOImpl();

    long productID = Long.parseLong(req.getParameter("hiddenItemID"));
    Double enteredOffer = Double.valueOf(req.getParameter("offer"));

    User supposedBidder = (User) req.getSession().getAttribute("loggedUser");
    Product existingProduct = productDAO.getByProductId(productID);
    Long biddingID = existingProduct.getBiddingByProduct().getBiddingId();
    Double existingOffer = existingProduct.getBiddingByProduct().getBestOffer();

    if (enteredOffer <= existingOffer) {
      req.getSession().setAttribute("errorExists",true);
      String errorMessage =
          "The indicated price offer cannot be less than or equal to the existing offer.";
      req.getSession().setAttribute("errorMessage", errorMessage);
    } else {
      req.getSession().removeAttribute("errorExists");
      req.getSession().removeAttribute("errorMessage");
      biddingDAO.editBiddingBestOfferAndBidder(supposedBidder,enteredOffer,biddingID);
    }
    resp.sendRedirect("product_table");
  }
}
