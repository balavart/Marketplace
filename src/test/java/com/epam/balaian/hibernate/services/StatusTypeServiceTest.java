package com.epam.balaian.hibernate.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.epam.balaian.hibernate.dao.StatusTypeDAO;
import com.epam.balaian.hibernate.model.StatusType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class StatusTypeServiceTest {

  @Mock private StatusTypeDAO statusTypeDAO;

  @InjectMocks
  private StatusTypeService statusTypeService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void checkStatusTypePresenceTrue() {
    StatusType statusReceived = new StatusType(1);

    given(statusTypeDAO.getStatusById(anyInt())).willReturn(statusReceived);

    final boolean actualResult = statusTypeService.checkStatusTypePresence(statusReceived);

    assertThat(actualResult).isTrue();
    verify(statusTypeDAO, times(1)).getStatusById(1);
  }

  @Test
  void checkStatusTypePresenceFalse() {
    given(statusTypeDAO.getStatusById(anyInt())).willReturn(null);

   final boolean actualResult = statusTypeService.checkStatusTypePresence(new StatusType(1));

    assertThat(actualResult).isFalse();
    verify(statusTypeDAO, times(1)).getStatusById(1);
  }

  @Test
  void checkStatusTypePresenceException() {
    assertThrows(
        Exception.class,
        () -> {
          given(statusTypeDAO.getStatusById(anyInt())).willThrow(Exception.class);

          statusTypeService.checkStatusTypePresence(any(StatusType.class));
        });
  }
}
