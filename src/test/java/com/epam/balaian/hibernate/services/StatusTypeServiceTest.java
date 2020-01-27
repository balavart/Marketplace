package com.epam.balaian.hibernate.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.epam.balaian.hibernate.dao.StatusTypeDAO;
import com.epam.balaian.hibernate.model.StatusType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class StatusTypeServiceTest {

  @Mock private StatusTypeDAO statusTypeDAO;

  private StatusTypeService statusTypeService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    this.statusTypeService = new StatusTypeService(statusTypeDAO);
  }

  @Test
  void checkStatusTypePresenceTrue() {
    given(statusTypeDAO.getStatusById(1)).willReturn(new StatusType(1));
    boolean statusExists = statusTypeService.checkStatusTypePresence(new StatusType(1));

    assertThat(statusExists).isTrue();

    verify(statusTypeDAO, times(1)).getStatusById(1);
  }

  @Test
  void checkStatusTypePresenceFalse() {
    given(statusTypeDAO.getStatusById(1)).willReturn(null);
    boolean statusExists = statusTypeService.checkStatusTypePresence(new StatusType(1));

    assertThat(statusExists).isFalse();

    verify(statusTypeDAO, times(1)).getStatusById(1);
  }

  @Test
  void checkStatusTypePresenceException() {
    assertThrows(
        Exception.class,
        () -> {
          given(statusTypeDAO.getStatusById(any(Integer.class))).willThrow(Exception.class);
          statusTypeService.checkStatusTypePresence(any(StatusType.class));
        });
  }
}
