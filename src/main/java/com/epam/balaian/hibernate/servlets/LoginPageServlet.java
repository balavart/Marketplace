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
 * @created 31.01.2020
 * @since 1.8
 */
public class LoginPageServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    UserDAO userDAO = new UserDAOImpl();

    String loginName = req.getParameter("login");
    String password = req.getParameter("password");

    User loggedUser = userDAO.getUserByLoginNameAndPassword(loginName, password);

    if (Objects.nonNull(loggedUser)) {

      req.getSession().removeAttribute("loggedUser");
      req.getSession().removeAttribute("guestMod");

      Role role = loggedUser.getUserRole();

      req.getSession().setAttribute("loggedUser", loggedUser);

      moveToRolePage(req, resp, role);
    } else {

      Role unknownRole = new Role();
      unknownRole.setRoleTitle("Unknown");

      moveToRolePage(req, resp, unknownRole);
    }
  }

  private void moveToRolePage(HttpServletRequest req, HttpServletResponse resp, Role role)
      throws ServletException, IOException {
    switch (role.getRoleTitle()) {
      case "Administrator":
        resp.sendRedirect("product_table");
        break;
      case "User":
        resp.sendRedirect("product_table");
        break;
      case "Guest":
        resp.sendRedirect("guest_product_table");
        break;
      default:
        String errorMessage = "No user with this username and password";
        req.setAttribute("errorMessage", errorMessage);
        req.getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
        break;
    }
  }
}
