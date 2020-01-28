package com.epam.balaian.hibernate.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.epam.balaian.hibernate.dao.RoleDAO;
import com.epam.balaian.hibernate.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class RoleServiceTest {

  @Mock private RoleDAO roleDAO;

  private RoleService roleService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    this.roleService = new RoleService(roleDAO);
  }

  @Test
  void checkRolePresenceTrue() {
    Role roleReceived = new Role(1);

    given(roleDAO.getRoleById(any(Integer.TYPE))).willReturn(roleReceived);
    boolean roleExists = roleService.checkRolePresence(roleReceived);

    assertThat(roleExists).isTrue();

    verify(roleDAO, times(1)).getRoleById(1);
  }

  @Test
  void checkRolePresenceFalse() {
    given(roleDAO.getRoleById(any(Integer.TYPE))).willReturn(null);
    boolean roleExists = roleService.checkRolePresence(new Role(1));

    assertThat(roleExists).isFalse();

    verify(roleDAO, times(1)).getRoleById(1);
  }

  @Test
  void checkRolePresenceException() {
    assertThrows(
        Exception.class,
        () -> {
          given(roleDAO.getRoleById(any(Integer.TYPE))).willThrow(Exception.class);
          roleService.checkRolePresence(any(Role.class));
        });
  }
}
