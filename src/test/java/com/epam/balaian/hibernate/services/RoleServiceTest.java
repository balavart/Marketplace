package com.epam.balaian.hibernate.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.epam.balaian.hibernate.dao.RoleDAO;
import com.epam.balaian.hibernate.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class RoleServiceTest {

  @Mock private RoleDAO roleDAO;

  @InjectMocks
  private RoleService roleService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void checkRolePresenceTrue() {
    Role roleReceived = new Role(1);

    given(roleDAO.getRoleById(anyInt())).willReturn(roleReceived);

   final boolean actualResult = roleService.checkRolePresence(roleReceived);

    assertThat(actualResult).isTrue();
    verify(roleDAO, times(1)).getRoleById(1);
  }

  @Test
  void checkRolePresenceFalse() {
    given(roleDAO.getRoleById(anyInt())).willReturn(null);

    final boolean actualResult = roleService.checkRolePresence(new Role(1));

    assertThat(actualResult).isFalse();
    verify(roleDAO, times(1)).getRoleById(1);
  }

  @Test
  void checkRolePresenceException() {
    assertThrows(
        Exception.class,
        () -> {
          given(roleDAO.getRoleById(anyInt())).willThrow(Exception.class);

          roleService.checkRolePresence(any(Role.class));
        });
  }
}
