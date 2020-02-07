package com.epam.balaian.hibernate.model;

import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * @author Vardan Balaian
 * @created 24.01.2020
 * @since 1.8
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "status_type", schema = "marketplace")
public class StatusType {

  private Integer statusId;
  private String statusTitle;
  private List<Bidding> biddingByStatus;

  public StatusType() {}

  public StatusType(Integer statusId) {
    this.statusId = statusId;
  }

  public StatusType(String statusTitle) {
    this.statusTitle = statusTitle;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "status_id", nullable = false)
  public Integer getStatusId() {
    return statusId;
  }

  public void setStatusId(Integer statusId) {
    this.statusId = statusId;
  }

  @Basic
  @Column(name = "status_title", nullable = false, length = 20)
  public String getStatusTitle() {
    return statusTitle;
  }

  public void setStatusTitle(String statusTitle) {
    this.statusTitle = statusTitle;
  }

  @OneToMany(mappedBy = "biddingStatus")
  public List<Bidding> getBiddingByStatus() {
    return biddingByStatus;
  }

  public void setBiddingByStatus(List<Bidding> biddingByStatus) {
    this.biddingByStatus = biddingByStatus;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StatusType that = (StatusType) o;
    return Objects.equals(statusId, that.statusId)
        && Objects.equals(statusTitle, that.statusTitle)
        && Objects.equals(biddingByStatus, that.biddingByStatus);
  }

  @Override
  public int hashCode() {
    return Objects.hash(statusId, statusTitle, biddingByStatus);
  }
}
