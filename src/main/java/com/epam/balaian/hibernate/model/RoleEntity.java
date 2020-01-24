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
@Table(name = "role", schema = "marketplace", catalog = "")
public class RoleEntity {

  private Integer roleId;
  private String roleTitle;
  private Collection<UserEntity> usersByRoleId;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "role_id", nullable = false)
  public Integer getRoleId() {
    return roleId;
  }

  public void setRoleId(Integer roleId) {
    this.roleId = roleId;
  }

  @Basic
  @Column(name = "role_title", nullable = false, length = 20)
  public String getRoleTitle() {
    return roleTitle;
  }

  public void setRoleTitle(String roleTitle) {
    this.roleTitle = roleTitle;
  }

  @OneToMany(mappedBy = "roleByRoleIdFk")
  public Collection<UserEntity> getUsersByRoleId() {
    return usersByRoleId;
  }

  public void setUsersByRoleId(Collection<UserEntity> usersByRoleId) {
    this.usersByRoleId = usersByRoleId;
  }

  @Override
  public String toString() {
    return "RoleEntity{"
        + "roleId="
        + roleId
        + ", roleTitle='"
        + roleTitle
        + '\''
        + ", usersByRoleId="
        + usersByRoleId
        + '}';
  }
}
