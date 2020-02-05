package com.epam.balaian.hibernate.servlets;

import com.epam.balaian.hibernate.dao.ProductDAO;
import com.epam.balaian.hibernate.dao.impl.ProductDAOImpl;
import com.epam.balaian.hibernate.model.Product;
import com.epam.balaian.hibernate.model.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Vardan Balaian
 * @created 31.01.2020
 * @since 1.8
 */
public class ProductTablePageServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    ProductDAO productDAO = new ProductDAOImpl();

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
}
