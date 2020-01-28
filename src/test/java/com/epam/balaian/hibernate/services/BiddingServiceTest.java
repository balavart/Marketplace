package com.epam.balaian.hibernate.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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
  void checkBiddingAdditionTrue() {
    Bidding addedBidding = new Bidding(555.55, Date.valueOf("2021-03-31"), new StatusType(), 1L);

    given(biddingDAO.addBidding(any(Bidding.class))).willReturn(addedBidding);
    boolean biddingExists = biddingService.checkBiddingAddition(addedBidding);

    assertThat(biddingExists).isTrue();

    verify(biddingDAO, times(1)).addBidding(addedBidding);
    verify(biddingDAO, never()).deleteBidding(any(Long.TYPE));
  }

  @Test
  void checkBiddingAdditionFalse() {
    given(biddingDAO.addBidding(any(Bidding.class))).willReturn(null);
    boolean biddingExists = biddingService.checkBiddingAddition(new Bidding(1L));

    assertThat(biddingExists).isFalse();

    verify(biddingDAO, times(1)).addBidding(new Bidding(1L));
    verify(biddingDAO, never()).deleteBidding(any(Long.TYPE));
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
    given(biddingDAO.getBiddingById(any(Long.TYPE))).willReturn(biddingReceived);
    boolean biddingExists = biddingService.checkBiddingPresence(biddingReceived);

    assertThat(biddingExists).isTrue();

    verify(biddingDAO, times(1)).getBiddingById(1L);
    verify(biddingDAO, never()).deleteBidding(any(Long.TYPE));
  }

  @Test
  void checkBiddingPresenceFalse() {
    given(biddingDAO.getBiddingById(any(Long.TYPE))).willReturn(null);
    boolean biddingExists = biddingService.checkBiddingPresence(new Bidding(1L));

    assertThat(biddingExists).isFalse();

    verify(biddingDAO, times(1)).getBiddingById(1L);
    verify(biddingDAO, never()).deleteBidding(any(Long.TYPE));
  }

  @Test
  void checkBiddingPresenceException() {
    assertThrows(
        Exception.class,
        () -> {
          given(biddingDAO.getBiddingById(any(Long.class))).willThrow(Exception.class);
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
    boolean biddingExists = biddingService.checkBiddingEditing(editedBidding);

    assertThat(biddingExists).isTrue();

    verify(biddingDAO, times(1))
        .editBidding(isA(Double.TYPE), isA(Date.class), isA(StatusType.class), isA(Long.TYPE));
    verify(biddingDAO, never()).deleteBidding(any(Long.TYPE));
  }

  @Test
  void checkBiddingEditingFalse() {
    given(
            biddingDAO.editBidding(
                isA(Double.TYPE), isA(Date.class), isA(StatusType.class), isA(Long.TYPE)))
        .willReturn(null);
    boolean biddingExists =
        biddingService.checkBiddingEditing(
            new Bidding(555.55, Date.valueOf("2021-03-31"), new StatusType(), 1L));

    assertThat(biddingExists).isFalse();

    verify(biddingDAO, times(1))
        .editBidding(isA(Double.TYPE), isA(Date.class), isA(StatusType.class), isA(Long.TYPE));
    verify(biddingDAO, never()).deleteBidding(any(Long.TYPE));
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

    given(biddingDAO.deleteBidding(any(Long.TYPE))).willReturn(deletedBidding);
    boolean biddingExists = biddingService.checkBiddingRemoval(deletedBidding);

    assertThat(biddingExists).isTrue();

    verify(biddingDAO, times(1)).deleteBidding(any(Long.TYPE));
    verify(biddingDAO, atLeastOnce()).deleteBidding(any(Long.TYPE));
  }

  @Test
  void checkBiddingRemovalFalse() {
    given(biddingDAO.deleteBidding(any(Long.TYPE))).willReturn(null);
    boolean biddingExists =
        biddingService.checkBiddingRemoval(
            new Bidding(555.55, Date.valueOf("2021-03-31"), new StatusType(), 1L));

    assertThat(biddingExists).isFalse();

    verify(biddingDAO, times(1)).deleteBidding(any(Long.TYPE));
    verify(biddingDAO, atLeastOnce()).deleteBidding(any(Long.TYPE));
  }

  @Test
  void checkBiddingRemovalException() {
    assertThrows(
        Exception.class,
        () -> {
          given(biddingDAO.deleteBidding(any(Long.TYPE))).willThrow(Exception.class);
          biddingService.checkBiddingRemoval(any(Bidding.class));
        });
  }

  @Test
  void checkAllBiddingPresenceTrue() {
    List<Bidding> allExistingBidding =
        Arrays.asList(new Bidding(1L), new Bidding(2L), new Bidding(3L));

    given(biddingDAO.getAllBidding()).willReturn(allExistingBidding);
    boolean biddingExists = biddingService.checkAllBiddingPresence(allExistingBidding);

    assertThat(biddingExists).isTrue();

    verify(biddingDAO, times(1)).getAllBidding();
    verify(biddingDAO, never()).deleteBidding(any(Long.TYPE));
  }

  @Test
  void checkAllBiddingPresenceFalse() {
    given(biddingDAO.getAllBidding()).willReturn(null);
    boolean biddingExists =
        biddingService.checkAllBiddingPresence(
            Arrays.asList(new Bidding(1L), new Bidding(2L), new Bidding(3L)));

    assertThat(biddingExists).isFalse();

    verify(biddingDAO, times(1)).getAllBidding();
    verify(biddingDAO, never()).deleteBidding(any(Long.TYPE));
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

    given(biddingDAO.getAllBiddingByIdSupposedBidder(any(Long.TYPE)))
        .willReturn(allExistingBiddingBySupposedBidder);
    boolean biddingExists =
        biddingService.checkAllBiddingPresenceByIdSupposedBidder(
            allExistingBiddingBySupposedBidder, supposedBidder);

    assertThat(biddingExists).isTrue();

    verify(biddingDAO, times(1)).getAllBiddingByIdSupposedBidder(any(Long.TYPE));
    verify(biddingDAO, never()).deleteBidding(any(Long.TYPE));
  }

  @Test
  void checkAllBiddingPresenceByIdSupposedBidderFalse() {
    List<Bidding> allExistingBiddingBySupposedBidder =
        Arrays.asList(new Bidding(1L), new Bidding(2L), new Bidding(3L));
    User supposedBidder = new User(1L);

    given(biddingDAO.getAllBiddingByIdSupposedBidder(any(Long.TYPE))).willReturn(null);
    boolean biddingExists =
        biddingService.checkAllBiddingPresenceByIdSupposedBidder(
            allExistingBiddingBySupposedBidder, supposedBidder);

    assertThat(biddingExists).isFalse();

    verify(biddingDAO, times(1)).getAllBiddingByIdSupposedBidder(any(Long.TYPE));
    verify(biddingDAO, never()).deleteBidding(any(Long.TYPE));
  }

  @Test
  void checkAllBiddingPresenceByIdSupposedBidderException() {
    assertThrows(
        Exception.class,
        () -> {
          given(biddingDAO.getAllBiddingByIdSupposedBidder(any(Long.TYPE)))
              .willThrow(Exception.class);
          biddingService.checkAllBiddingPresenceByIdSupposedBidder(any(), any(User.class));
        });
  }
}
