package com.epam.balaian.hibernate.model;

import java.util.Collection;
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
 * @author balavart
 * @created 24.01.2020
 * @since 1.8
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "status_type", schema = "marketplace", catalog = "")
public class StatusTypeEntity {

  private Integer statusId;
  private String statusTitle;
  private Collection<BiddingEntity> biddingsByStatusId;

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

  @OneToMany(mappedBy = "statusTypeByStatusIdFk")
  public Collection<BiddingEntity> getBiddingsByStatusId() {
    return biddingsByStatusId;
  }

  public void setBiddingsByStatusId(Collection<BiddingEntity> biddingsByStatusId) {
    this.biddingsByStatusId = biddingsByStatusId;
  }

  @Override
  public String toString() {
    return "StatusTypeEntity{"
        + "statusId="
        + statusId
        + ", statusTitle='"
        + statusTitle
        + '\''
        + ", biddingsByStatusId="
        + biddingsByStatusId
        + '}';
  }
}
