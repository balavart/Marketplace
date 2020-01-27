package com.epam.balaian.hibernate.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.epam.balaian.hibernate.dao.UserDAO;
import com.epam.balaian.hibernate.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UserServiceTest {

  @Mock private UserDAO userDAO;

  private UserService userService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    this.userService = new UserService(userDAO);
  }

  @Test
  public void checkUserPresenceTrue() {
    given(userDAO.getUserById(1L)).willReturn(new User(1L));
    boolean userExists = userService.checkUserPresence(new User(1L));

    assertThat(userExists).isTrue();

    verify(userDAO, times(1)).getUserById(1L);
  }

  @Test
  public void checkUserPresenceFalse() {
    given(userDAO.getUserById(1L)).willReturn(null);
    boolean userExists = userService.checkUserPresence(new User(1L));

    assertThat(userExists).isFalse();

    verify(userDAO, times(1)).getUserById(1L);
  }

  @Test
  public void checkUserPresenceException() {
    assertThrows(
        Exception.class,
        () -> {
          given(userDAO.getUserById(any(Long.class))).willThrow(Exception.class);
          userService.checkUserPresence(any(User.class));
        });
  }

  @Test
  public void addUserTest() {
    doNothing().when(userDAO).addUser(any(User.class));

    userDAO.addUser(new User(1L));

    verify(userDAO, times(1)).addUser(new User(1L));
  }

  @Test
  public void addUserException() {
    assertThrows(
        Exception.class,
        () -> {
          doThrow().when(userDAO).addUser(any(User.class));
        });
  }
}
