package com.epam.balaian.hibernate.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
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
  void checkUserAdditionTrue() {
    User addedUser = new User(1L);

    given(userDAO.addUser(any(User.class))).willReturn(addedUser);
    boolean userExists = userService.checkUserAddition(addedUser);

    assertThat(userExists).isTrue();

    verify(userDAO, times(1)).addUser(addedUser);
  }

  @Test
  void checkUserAdditionFalse() {
    given(userDAO.addUser(any(User.class))).willReturn(null);
    boolean userExists = userService.checkUserAddition(new User(1L));

    assertThat(userExists).isFalse();

    verify(userDAO, times(1)).addUser(new User(1L));
  }

  @Test
  void checkUserAdditionException() {
    assertThrows(
        Exception.class,
        () -> {
          given(userDAO.addUser(any(User.class))).willThrow(Exception.class);
          userService.checkUserAddition(any(User.class));
        });
  }

  @Test
  public void checkUserPresenceTrue() {
    User userReceived = new User(1L);

    given(userDAO.getUserById(any(Long.TYPE))).willReturn(userReceived);
    boolean userExists = userService.checkUserPresence(userReceived);

    assertThat(userExists).isTrue();

    verify(userDAO, times(1)).getUserById(1L);
  }

  @Test
  public void checkUserPresenceFalse() {
    given(userDAO.getUserById(any(Long.TYPE))).willReturn(null);
    boolean userExists = userService.checkUserPresence(new User(1L));

    assertThat(userExists).isFalse();

    verify(userDAO, times(1)).getUserById(1L);
  }

  @Test
  public void checkUserPresenceException() {
    assertThrows(
        Exception.class,
        () -> {
          given(userDAO.getUserById(any(Long.TYPE))).willThrow(Exception.class);
          userService.checkUserPresence(any(User.class));
        });
  }
}
