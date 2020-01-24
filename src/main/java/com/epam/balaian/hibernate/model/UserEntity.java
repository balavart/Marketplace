package com.epam.balaian.hibernate.model;

import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "user", schema = "marketplace", catalog = "")
public class UserEntity {

  private Long userId;
  private String loginName;
  private String password;
  private String fullName;
  private String city;
  private String email;
  private String phoneNumber;
  private Collection<BiddingEntity> biddingsByUserId;
  private Collection<ProductEntity> productsByUserId;
  private RoleEntity roleByRoleIdFk;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id", nullable = false)
  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  @Basic
  @Column(name = "login_name", nullable = false, length = 20)
  public String getLoginName() {
    return loginName;
  }

  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }

  @Basic
  @Column(name = "password", nullable = false, length = 20)
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Basic
  @Column(name = "full_name", nullable = false, length = 40)
  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  @Basic
  @Column(name = "city", nullable = false, length = 30)
  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  @Basic
  @Column(name = "email", nullable = false, length = 40)
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Basic
  @Column(name = "phone_number", nullable = false, length = 12)
  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  @OneToMany(mappedBy = "userBySupposedBidderIdFk")
  public Collection<BiddingEntity> getBiddingsByUserId() {
    return biddingsByUserId;
  }

  public void setBiddingsByUserId(Collection<BiddingEntity> biddingsByUserId) {
    this.biddingsByUserId = biddingsByUserId;
  }

  @OneToMany(mappedBy = "userByProductOwnerIdFk")
  public Collection<ProductEntity> getProductsByUserId() {
    return productsByUserId;
  }

  public void setProductsByUserId(Collection<ProductEntity> productsByUserId) {
    this.productsByUserId = productsByUserId;
  }

  @ManyToOne
  @JoinColumn(name = "role_id_fk", referencedColumnName = "role_id", nullable = false)
  public RoleEntity getRoleByRoleIdFk() {
    return roleByRoleIdFk;
  }

  public void setRoleByRoleIdFk(RoleEntity roleByRoleIdFk) {
    this.roleByRoleIdFk = roleByRoleIdFk;
  }

  @Override
  public String toString() {
    return "UserEntity{"
        + "userId="
        + userId
        + ", loginName='"
        + loginName
        + '\''
        + ", password='"
        + password
        + '\''
        + ", fullName='"
        + fullName
        + '\''
        + ", city='"
        + city
        + '\''
        + ", email='"
        + email
        + '\''
        + ", phoneNumber='"
        + phoneNumber
        + '\''
        + ", biddingsByUserId="
        + biddingsByUserId
        + ", productsByUserId="
        + productsByUserId
        + ", roleByRoleIdFk="
        + roleByRoleIdFk
        + '}';
  }
}
