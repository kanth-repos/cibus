package com.cibus.dtos;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.cibus.annotations.CreateGroup;
import com.cibus.annotations.UpdateGroup;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class UserDto implements Serializable {
  // Java Bean for User
  public void setPassword(String password) {
    if(password != null) {
      this.password = BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public String getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  public String getMobile() {
    return mobile;
  }

  public String getEmail() {
    return email;
  }

  // Model attributes for User
  @NotNull(groups = {CreateGroup.class})
  @NotBlank(groups = {CreateGroup.class, UpdateGroup.class})
  private String type;

  @NotNull(groups = {CreateGroup.class})
  @NotBlank(groups = {CreateGroup.class, UpdateGroup.class})
  private String name;

  @NotNull(groups = {CreateGroup.class})
  @NotBlank(groups = {CreateGroup.class, UpdateGroup.class})
  private String mobile;

  @NotNull(groups = {CreateGroup.class})
  @NotBlank(groups = {CreateGroup.class, UpdateGroup.class})
  @Email
  private String email;

  @NotNull(groups = {CreateGroup.class})
  @NotBlank(groups = {CreateGroup.class, UpdateGroup.class})
  private String password;
};
