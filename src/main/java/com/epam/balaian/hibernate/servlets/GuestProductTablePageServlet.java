package com.epam.balaian.hibernate.servlets;

import com.epam.balaian.hibernate.dao.ProductDAO;
import com.epam.balaian.hibernate.dao.impl.ProductDAOImpl;
import com.epam.balaian.hibernate.model.Product;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Vardan Balaian
 * @created 05.02.2020
 * @since 1.8
 */
public class GuestProductTablePageServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    req.getSession().removeAttribute("loggedUser");

    req.getSession().setAttribute("guestMod", true);

    ProductDAO productDAO = new ProductDAOImpl();

    List<Product> productList = productDAO.getAllProducts();
    int productNumber = productList.size();
    String relevantStatus = "At auction";
    String soldStatus = "Sold";

    req.setAttribute("productList", productList);
    req.setAttribute("productNumber", productNumber);
    req.setAttribute("relevantStatus", relevantStatus);
    req.setAttribute("soldStatus", soldStatus);

    req.getServletContext().getRequestDispatcher("/jsp/guest_product_table.jsp").forward(req, resp);
  }
}
