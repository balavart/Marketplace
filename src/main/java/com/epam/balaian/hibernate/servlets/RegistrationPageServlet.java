package com.epam.balaian.hibernate.servlets;

import com.epam.balaian.hibernate.dao.UserDAO;
import com.epam.balaian.hibernate.dao.impl.UserDAOImpl;
import com.epam.balaian.hibernate.model.Role;
import com.epam.balaian.hibernate.model.User;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Vardan Balaian
 * @created 1/31/2020
 * @since 1.8
 */
public class RegistrationPageServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    req.getServletContext().getRequestDispatcher("/jsp/registration.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    UserDAO userDAO = new UserDAOImpl();

    String loginName = req.getParameter("login");
    String password = req.getParameter("password");
    String fullName = req.getParameter("fullName");
    String city = req.getParameter("city");
    String email = req.getParameter("email");
    String phoneNumber = req.getParameter("phone");
    Role role = new Role(2);

    User loggedUser = userDAO.getUserByLoginName(loginName);

    if (Objects.nonNull(loggedUser)) {

      String errorMessage = "The login you entered is reserved";

      req.setAttribute("errorMessage", errorMessage);

      req.getServletContext().getRequestDispatcher("/jsp/registration.jsp").forward(req, resp);
    } else {

      req.getSession().removeAttribute("loggedUser");
      req.getSession().removeAttribute("guestMod");

      User newUser = new User(loginName, password, fullName, city, email, phoneNumber, role);

      userDAO.addUser(newUser);

      req.getSession().setAttribute("loggedUser", newUser);

      resp.sendRedirect("product_table");
    }
  }
}
