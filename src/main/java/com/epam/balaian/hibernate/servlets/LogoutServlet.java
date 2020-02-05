package com.epam.balaian.hibernate.servlets;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Vardan Balaian
 * @created 2/5/2020
 * @since 1.8
 */
public class LogoutServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    req.getSession().invalidate();

    resp.sendRedirect(super.getServletContext().getContextPath());
  }
}
