package com.epam.balaian.hibernate.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.epam.balaian.hibernate.dao.BiddingDAO;
import com.epam.balaian.hibernate.model.Bidding;
import com.epam.balaian.hibernate.model.StatusType;
import com.epam.balaian.hibernate.model.User;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

//@TestInstance(Lifecycle.PER_CLASS)
class BiddingServiceTest {

  @Mock private BiddingDAO biddingDAO;

  @InjectMocks
  private BiddingService biddingService;

  @BeforeEach
   void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void checkBiddingAdditionTrue() {
    Bidding addedBidding = new Bidding(555.55, Date.valueOf("2021-03-31"), new StatusType(), 1L);

    given(biddingDAO.addBidding(any(Bidding.class))).willReturn(addedBidding);

    final boolean actualResult = biddingService.checkBiddingAddition(addedBidding);

    assertThat(actualResult).isTrue();
    verify(biddingDAO, times(1)).addBidding(addedBidding);
    verify(biddingDAO, never()).deleteBidding(anyLong());
  }

  @Test
  void checkBiddingAdditionFalse() {
    biddingService = new BiddingService(biddingDAO);

    given(biddingDAO.addBidding(any(Bidding.class))).willReturn(null);

    final boolean actualResult = biddingService.checkBiddingAddition(new Bidding(1L));

    assertThat(actualResult).isFalse();
    verify(biddingDAO, times(1)).addBidding(new Bidding(1L));
    verify(biddingDAO, never()).deleteBidding(anyLong());
  }

  @Test
  void checkBiddingAdditionException() {
    assertThrows(
        Exception.class,
        () -> {
          given(biddingDAO.addBidding(any(Bidding.class))).willThrow(Exception.class);

          biddingService.checkBiddingAddition(any(Bidding.class));
        });
  }

  @Test
  void checkBiddingPresenceTrue() {
    Bidding biddingReceived = new Bidding(555.55, Date.valueOf("2021-03-31"), new StatusType(), 1L);

    given(biddingDAO.getBiddingById(anyLong())).willReturn(biddingReceived);

    final boolean actualResult = biddingService.checkBiddingPresence(biddingReceived);

    assertThat(actualResult).isTrue();
    verify(biddingDAO, times(1)).getBiddingById(1L);
    verify(biddingDAO, never()).deleteBidding(anyLong());
  }

  @Test
  void checkBiddingPresenceFalse() {
    given(biddingDAO.getBiddingById(anyLong())).willReturn(null);

    final boolean actualResult = biddingService.checkBiddingPresence(new Bidding(1L));

    assertThat(actualResult).isFalse();
    verify(biddingDAO, times(1)).getBiddingById(1L);
    verify(biddingDAO, never()).deleteBidding(anyLong());
  }

  @Test
  void checkBiddingPresenceException() {
    assertThrows(
        Exception.class,
        () -> {
          given(biddingDAO.getBiddingById(anyLong())).willThrow(Exception.class);

          biddingService.checkBiddingPresence(any(Bidding.class));
        });
  }

  @Test
  void checkBiddingEditingTrue() {
    Bidding editedBidding = new Bidding(555.55, Date.valueOf("2021-03-31"), new StatusType(), 1L);

    given(
            biddingDAO.editBidding(
                isA(Double.TYPE), isA(Date.class), isA(StatusType.class), isA(Long.TYPE)))
        .willReturn(editedBidding);

    final boolean actualResult = biddingService.checkBiddingEditing(editedBidding);

    assertThat(actualResult).isTrue();
    verify(biddingDAO, times(1))
        .editBidding(isA(Double.TYPE), isA(Date.class), isA(StatusType.class), isA(Long.TYPE));
    verify(biddingDAO, never()).deleteBidding(anyLong());
  }

  @Test
  void checkBiddingEditingFalse() {
    given(
            biddingDAO.editBidding(
                isA(Double.TYPE), isA(Date.class), isA(StatusType.class), isA(Long.TYPE)))
        .willReturn(null);

    final boolean actualResult =
        biddingService.checkBiddingEditing(
            new Bidding(555.55, Date.valueOf("2021-03-31"), new StatusType(), 1L));

    assertThat(actualResult).isFalse();
    verify(biddingDAO, times(1))
        .editBidding(isA(Double.TYPE), isA(Date.class), isA(StatusType.class), isA(Long.TYPE));
    verify(biddingDAO, never()).deleteBidding(anyLong());
  }

  @Test
  void checkBiddingEditingException() {
    assertThrows(
        Exception.class,
        () -> {
          given(
                  biddingDAO.editBidding(
                      isA(Double.TYPE), isA(Date.class), isA(StatusType.class), isA(Long.TYPE)))
              .willThrow(Exception.class);

          biddingService.checkBiddingEditing(any(Bidding.class));
        });
  }

  @Test
  void checkBiddingRemovalTrue() {
    Bidding deletedBidding = new Bidding(555.55, Date.valueOf("2021-03-31"), new StatusType(), 1L);

    given(biddingDAO.deleteBidding(anyLong())).willReturn(deletedBidding);

    final boolean actualResult = biddingService.checkBiddingRemoval(deletedBidding);

    assertThat(actualResult).isTrue();
    verify(biddingDAO, times(1)).deleteBidding(anyLong());
    verify(biddingDAO, atLeastOnce()).deleteBidding(anyLong());
  }

  @Test
  void checkBiddingRemovalFalse() {
    given(biddingDAO.deleteBidding(anyLong())).willReturn(null);

    final boolean actualResult =
        biddingService.checkBiddingRemoval(
            new Bidding(555.55, Date.valueOf("2021-03-31"), new StatusType(), 1L));

    assertThat(actualResult).isFalse();
    verify(biddingDAO, times(1)).deleteBidding(anyLong());
    verify(biddingDAO, atLeastOnce()).deleteBidding(anyLong());
  }

  @Test
  void checkBiddingRemovalException() {
    assertThrows(
        Exception.class,
        () -> {
          given(biddingDAO.deleteBidding(anyLong())).willThrow(Exception.class);

          biddingService.checkBiddingRemoval(any(Bidding.class));
        });
  }

  @Test
  void checkAllBiddingPresenceTrue() {
    List<Bidding> allExistingBidding =
        Arrays.asList(new Bidding(1L), new Bidding(2L), new Bidding(3L));

    given(biddingDAO.getAllBidding()).willReturn(allExistingBidding);

    final boolean actualResult = biddingService.checkAllBiddingPresence(allExistingBidding);

    assertThat(actualResult).isTrue();
    verify(biddingDAO, times(1)).getAllBidding();
    verify(biddingDAO, never()).deleteBidding(anyLong());
  }

  @Test
  void checkAllBiddingPresenceFalse() {
    given(biddingDAO.getAllBidding()).willReturn(null);

    final boolean actualResult =
        biddingService.checkAllBiddingPresence(
            Arrays.asList(new Bidding(1L), new Bidding(2L), new Bidding(3L)));

    assertThat(actualResult).isFalse();
    verify(biddingDAO, times(1)).getAllBidding();
    verify(biddingDAO, never()).deleteBidding(anyLong());
  }

  @Test
  void checkAllBiddingPresenceException() {
    assertThrows(
        Exception.class,
        () -> {
          given(biddingDAO.getAllBidding()).willThrow(Exception.class);

          biddingService.checkAllBiddingPresence(any());
        });
  }

  @Test
  void checkAllBiddingPresenceByIdSupposedBidderTrue() {
    List<Bidding> allExistingBiddingBySupposedBidder =
        Arrays.asList(new Bidding(1L), new Bidding(2L), new Bidding(3L));
    User supposedBidder = new User(1L);

    given(biddingDAO.getAllBiddingByIdSupposedBidder(anyLong()))
        .willReturn(allExistingBiddingBySupposedBidder);

    final boolean actualResult =
        biddingService.checkAllBiddingPresenceByIdSupposedBidder(
            allExistingBiddingBySupposedBidder, supposedBidder);

    assertThat(actualResult).isTrue();
    verify(biddingDAO, times(1)).getAllBiddingByIdSupposedBidder(anyLong());
    verify(biddingDAO, never()).deleteBidding(anyLong());
  }

  @Test
  void checkAllBiddingPresenceByIdSupposedBidderFalse() {
    List<Bidding> allExistingBiddingBySupposedBidder =
        Arrays.asList(new Bidding(1L), new Bidding(2L), new Bidding(3L));
    User supposedBidder = new User(1L);

    given(biddingDAO.getAllBiddingByIdSupposedBidder(anyLong())).willReturn(null);

   final boolean actualResult =
        biddingService.checkAllBiddingPresenceByIdSupposedBidder(
            allExistingBiddingBySupposedBidder, supposedBidder);

    assertThat(actualResult).isFalse();
    verify(biddingDAO, times(1)).getAllBiddingByIdSupposedBidder(anyLong());
    verify(biddingDAO, never()).deleteBidding(anyLong());
  }

  @Test
  void checkAllBiddingPresenceByIdSupposedBidderException() {
    assertThrows(
        Exception.class,
        () -> {
          given(biddingDAO.getAllBiddingByIdSupposedBidder(anyLong()))
              .willThrow(Exception.class);

          biddingService.checkAllBiddingPresenceByIdSupposedBidder(any(), any(User.class));
        });
  }
}
