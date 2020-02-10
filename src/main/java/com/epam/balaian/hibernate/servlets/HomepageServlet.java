package com.epam.balaian.hibernate.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Vardan Balaian
 * @created 1/30/2020
 * @since 1.8
 */
public class HomepageServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.getSession().removeAttribute("priceErrorExists");
    req.getSession().removeAttribute("priceErrorMessage");
    req.getSession().removeAttribute("ownerOfferErrorExists");
    req.getSession().removeAttribute("ownerOfferErrorMessage");

    req.getServletContext().getRequestDispatcher("/jsp/homepage.jsp").forward(req, resp);
  }
}
