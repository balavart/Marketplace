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
@Table(name = "role", schema = "marketplace")
public class Role {

  private Integer roleId;
  private String roleTitle;
  private List<User> usersByRole;

  public Role(Integer roleId) {
    this.roleId = roleId;
  }

  public Role() {}

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

  @OneToMany(mappedBy = "userRole")
  public List<User> getUsersByRole() {
    return usersByRole;
  }

  public void setUsersByRole(List<User> usersByRole) {
    this.usersByRole = usersByRole;
  }

  @Override
  public String toString() {
    return "Role{" + "roleId=" + roleId + ", roleTitle='" + roleTitle + '\'' + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Role role = (Role) o;
    return Objects.equals(roleId, role.roleId)
        && Objects.equals(roleTitle, role.roleTitle)
        && Objects.equals(usersByRole, role.usersByRole);
  }

  @Override
  public int hashCode() {
    return Objects.hash(roleId, roleTitle, usersByRole);
  }
}
