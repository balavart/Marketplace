package com.epam.balaian.hibernate.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.epam.balaian.hibernate.dao.BiddingDAO;
import com.epam.balaian.hibernate.model.Bidding;
import com.epam.balaian.hibernate.model.StatusType;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class BiddingServiceTest {

  @Mock private BiddingDAO biddingDAO;

  private BiddingService biddingService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    this.biddingService = new BiddingService(biddingDAO);
  }

  @Test
  void checkBiddingPresenceTrue() {
    given(biddingDAO.getBiddingById(1L)).willReturn(new Bidding(1L));
    boolean biddingExists = biddingService.checkBiddingPresence(new Bidding(1L));

    assertThat(biddingExists).isTrue();

    verify(biddingDAO, times(1)).getBiddingById(1L);
    verify(biddingDAO, never()).deleteBidding(any(Long.class));
  }

  @Test
  void checkBiddingPresenceFalse() {
    given(biddingDAO.getBiddingById(1L)).willReturn(null);
    boolean biddingExists = biddingService.checkBiddingPresence(new Bidding(1L));

    assertThat(biddingExists).isFalse();

    verify(biddingDAO, times(1)).getBiddingById(1L);
    verify(biddingDAO, never()).deleteBidding(any(Long.class));
  }

  @Test
  void checkBiddingPresenceException() {
    assertThrows(
        Exception.class,
        () -> {
          given(biddingDAO.getBiddingById(any(Long.class))).willThrow(Exception.class);
          biddingService.checkBiddingPresence(new Bidding(1L));
        });
  }

  @Test
  void addBiddingTest() {
    doNothing().when(biddingDAO).addBidding(any(Bidding.class));

    biddingDAO.addBidding(new Bidding(1L));

    verify(biddingDAO, times(1)).addBidding(new Bidding(1L));
    verify(biddingDAO, never()).deleteBidding(any(Long.class));
  }

  @Test
  void addBiddingException() {
    assertThrows(
        Exception.class,
        () -> {
          doThrow().when(biddingDAO).addBidding(isNull());
        });
  }

  @Test
  void editBiddingTest() {
    doNothing()
        .when(biddingDAO)
        .editBidding(isA(Double.class), isA(Date.class), isA(StatusType.class), isA(Long.class));

    biddingDAO.editBidding(222.22, Date.valueOf("2021-03-31"), new StatusType(), 1L);

    verify(biddingDAO, times(1))
        .editBidding(isA(Double.class), isA(Date.class), isA(StatusType.class), isA(Long.class));
    verify(biddingDAO, never()).deleteBidding(any(Long.class));
  }

  @Test
  void editBiddingException() {
    assertThrows(
        Exception.class,
        () -> {
          doThrow()
              .when(biddingDAO)
              .editBidding(
                  isA(Double.class), isA(Date.class), isA(StatusType.class), isA(Long.class));
        });
  }

  @Test
  void deleteBiddingTest() {
    doNothing().when(biddingDAO).deleteBidding(isA(Long.class));
    biddingDAO.deleteBidding(1L);

    verify(biddingDAO, times(1)).deleteBidding(1L);
    verify(biddingDAO, atLeastOnce()).deleteBidding(any(Long.class));
  }

  @Test
  void deleteBiddingException() {
    assertThrows(
        Exception.class,
        () -> {
          doThrow().when(biddingDAO).deleteBidding(any(Long.class));
        });
  }

  @Test
  void getAllBiddingTest() {
    List<Bidding> biddingList = Arrays.asList(new Bidding(1L), new Bidding(2L));

    when(biddingDAO.getAllBidding()).thenReturn(biddingList);

    biddingDAO.getAllBidding();

    verify(biddingDAO, times(1)).getAllBidding();
  }

  @Test
  void getAllBiddingException() {
    assertThrows(
        Exception.class,
        () -> {
          doThrow().when(biddingDAO).getAllBidding();
        });
  }

  @Test
  void getAllBiddingByIdSupposedBidderTest() {
    List<Bidding> biddingList = Arrays.asList(new Bidding(1L), new Bidding(2L));

    when(biddingDAO.getAllBiddingByIdSupposedBidder(any(Long.class))).thenReturn(biddingList);

    biddingDAO.getAllBiddingByIdSupposedBidder(any(Long.class));

    verify(biddingDAO, times(1)).getAllBiddingByIdSupposedBidder(any(Long.class));
  }

  @Test
  void getAllBiddingByIdSupposedBidderException() {
    assertThrows(
        Exception.class,
        () -> {
          doThrow().when(biddingDAO).getAllBiddingByIdSupposedBidder(any(Long.class));
        });
  }
}
