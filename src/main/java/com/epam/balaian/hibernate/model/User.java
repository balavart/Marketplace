package com.epam.balaian.hibernate.model;

import java.util.List;
import java.util.Objects;
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
 * @author Vardan Balaian
 * @created 24.01.2020
 * @since 1.8
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "user", schema = "marketplace")
public class User {

  private Long userId;
  private String loginName;
  private String password;
  private String fullName;
  private String city;
  private String email;
  private String phoneNumber;
  private List<Bidding> biddingByUser;
  private List<Product> productsByUser;
  private Role userRole;

  public User() {}

  public User(Long userId) {
    this.userId = userId;
  }

  public User(
      String loginName,
      String password,
      String fullName,
      String city,
      String email,
      String phoneNumber,
      Role role) {
    this.loginName = loginName;
    this.password = password;
    this.fullName = fullName;
    this.city = city;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.userRole = role;
  }

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

  @OneToMany(mappedBy = "userAsSupposedBidder")
  public List<Bidding> getBiddingByUser() {
    return biddingByUser;
  }

  public void setBiddingByUser(List<Bidding> biddingByUser) {
    this.biddingByUser = biddingByUser;
  }

  @OneToMany(mappedBy = "productOwner")
  public List<Product> getProductsByUser() {
    return productsByUser;
  }

  public void setProductsByUser(List<Product> productsByUser) {
    this.productsByUser = productsByUser;
  }

  @ManyToOne
  @JoinColumn(name = "role_id_fk", referencedColumnName = "role_id", nullable = false)
  public Role getUserRole() {
    return userRole;
  }

  public void setUserRole(Role userRole) {
    this.userRole = userRole;
  }

  @Override
  public String toString() {
    return "User{"
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
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(userId, user.userId)
        && Objects.equals(loginName, user.loginName)
        && Objects.equals(password, user.password)
        && Objects.equals(fullName, user.fullName)
        && Objects.equals(city, user.city)
        && Objects.equals(email, user.email)
        && Objects.equals(phoneNumber, user.phoneNumber)
        && Objects.equals(biddingByUser, user.biddingByUser)
        && Objects.equals(productsByUser, user.productsByUser)
        && Objects.equals(userRole, user.userRole);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        userId,
        loginName,
        password,
        fullName,
        city,
        email,
        phoneNumber,
        biddingByUser,
        productsByUser,
        userRole);
  }
}
