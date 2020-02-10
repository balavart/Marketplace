package com.epam.balaian.hibernate.servlets;

import com.epam.balaian.hibernate.model.User;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Vardan Balaian
 * @created 2/3/2020
 * @since 1.8
 */
public class AuthenticationFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) {}

  @Override
  public void doFilter(
      ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {

    HttpServletRequest req = (HttpServletRequest) servletRequest;
    HttpServletResponse resp = (HttpServletResponse) servletResponse;

    User loggedUser = (User) req.getSession().getAttribute("loggedUser");

    if (Objects.isNull(loggedUser)) {
      resp.sendRedirect("login");
    } else {
      filterChain.doFilter(req, resp);
    }
  }

  @Override
  public void destroy() {}
}
